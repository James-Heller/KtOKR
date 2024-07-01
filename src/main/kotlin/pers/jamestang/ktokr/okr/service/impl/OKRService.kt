package pers.jamestang.ktokr.okr.service.impl

import org.ktorm.database.Database
import org.ktorm.database.asIterable
import org.ktorm.dsl.*
import org.ktorm.entity.add
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList
import org.ktorm.jackson.JsonSqlType
import org.ktorm.schema.SqlType
import org.ktorm.support.mysql.jsonContains
import org.ktorm.support.mysql.jsonExtract
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pers.jamestang.ktokr.okr.entity.KeyResultStatus
import pers.jamestang.ktokr.okr.entity.OKRAlignment
import pers.jamestang.ktokr.okr.entity.vo.OKR
import pers.jamestang.ktokr.okr.repository.KeyResults
import pers.jamestang.ktokr.okr.repository.OKRAlignments
import pers.jamestang.ktokr.okr.repository.Objectives
import pers.jamestang.ktokr.okr.service.IOKRService
import pers.jamestang.ktokr.system.entity.LoginAdmin
import pers.jamestang.ktokr.system.repository.DepartmentMembers
import pers.jamestang.ktokr.system.repository.Departments
import pers.jamestang.ktokr.system.service.IDeptService

@Service
class OKRService(
    private val database: Database,
    private val deptService: IDeptService
) : IOKRService {


    override fun getList(): List<OKR> {

        val objectives = database.sequenceOf(Objectives).toList()
        val keyResults = database.sequenceOf(KeyResults).toList()

        return objectives.associateWith { o -> keyResults.filter { kr -> kr.objectiveId == o.id } }
            .map { (o, krs) -> OKR(o, krs) }
    }

    override fun getByUserId(adminId: Int): List<OKR> {

        val objectives =
            database.from(Objectives).select().where { Objectives.adminId eq adminId }.map(Objectives::createEntity)
        val keyResults =
            database.from(KeyResults).select().where { KeyResults.objectiveId inList objectives.map { it.id } }
                .map(KeyResults::createEntity)

        return objectives.associateWith { o -> keyResults.filter { kr -> kr.objectiveId == o.id } }
            .map { (o, krs) -> OKR(o, krs) }
    }

    @Transactional
    override fun createOKR(okr: OKR): Boolean {

        val objective = okr.objective
        val keyResults = okr.keyResults

        val currentUserId = 1
        objective.adminId = currentUserId

        val oResult = database.sequenceOf(Objectives).add(objective)

        val objectiveId = objective.id

        keyResults.forEach { it.objectiveId = objectiveId }

        val kResult = database.batchInsert(KeyResults) {
            keyResults.forEach { kr ->
                item {
                    set(it.objectiveId, kr.objectiveId)
                    set(it.title, kr.title)
                    set(it.description, kr.description)
                    set(it.targetValue, kr.targetValue)
                    set(it.currentValue, kr.currentValue)
                    set(it.measurementType, kr.measurementType)
                    set(it.status, KeyResultStatus.NOT_STARTED)
                }
            }
        }

        return oResult > 0 && kResult.sum() == keyResults.size
    }

    @Transactional
    override fun createAlignOKR(okr: OKR, masterObjectiveId: Int): Boolean {

        createOKR(okr)

        return database.sequenceOf(OKRAlignments).add(
            OKRAlignment {
                this.masterId = masterObjectiveId
                this.slaveId = okr.objective.id
            }
        ) == 1
    }


    override fun removeInvisibleOKR(list: List<OKR>): List<Int> {

//        val currentUserDept = (SecurityContextHolder.getContext().authentication.principal as LoginAdmin).getUserDept()

        val okrOwnerIds = list.map { it.objective.adminId }.distinct()


        val okrOwnersDeptIdList = okrOwnerIds.map { id ->
            database.from(Departments)
                .leftJoin(DepartmentMembers, on = Departments.id eq DepartmentMembers.deptId)
                .select(Departments.columns)
                .where { DepartmentMembers.deptMember.jsonContains(id) }.map(Departments::createEntity)
        }


        TODO()
    }

}