package pers.jamestang.ktokr.okr.service.impl

import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import pers.jamestang.ktokr.okr.entity.vo.OKR
import pers.jamestang.ktokr.okr.repository.KeyResults
import pers.jamestang.ktokr.okr.repository.Objectives
import pers.jamestang.ktokr.okr.service.IOKRService

@Service
class OKRService(
    private val database: Database,
): IOKRService {


    override fun getList(): List<OKR> {

        val objectives = database.sequenceOf(Objectives).toList()
        val keyResults = database.sequenceOf(KeyResults).toList()

        return objectives.associateWith {  o -> keyResults.filter { kr -> kr.objectiveId == o.id } }.map { (o, krs) -> OKR(o, krs) }
    }

    override fun getByUserId(adminId: Int): List<OKR> {

        val objectives = database.from(Objectives).select().where { Objectives.adminId eq adminId }.map(Objectives::createEntity)
        val keyResults = database.from(KeyResults).select().where { KeyResults.objectiveId inList objectives.map { it.id } }.map(KeyResults::createEntity)

        return objectives.associateWith {  o -> keyResults.filter { kr -> kr.objectiveId == o.id } }.map { (o, krs) -> OKR(o, krs) }
    }
}