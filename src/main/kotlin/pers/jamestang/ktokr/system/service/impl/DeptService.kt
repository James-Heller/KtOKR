package pers.jamestang.ktokr.system.service.impl

import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.*
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pers.jamestang.ktokr.system.entity.Department
import pers.jamestang.ktokr.system.repository.DepartmentMembers
import pers.jamestang.ktokr.system.repository.Departments
import pers.jamestang.ktokr.system.service.IDeptService
import pers.jamestang.ktokr.system.util.Page

@Service
class DeptService(
    private val database: Database
): IDeptService {

    @CachePut("deptList")
    override fun getList(): List<Department> {
        return database.sequenceOf(Departments).toList()
    }

    override fun getPageList(page: Int, size: Int): Page<Department> {

        val total = database.sequenceOf(Departments).count()
        val list = database.from(Departments).select(Departments.columns).limit((page - 1) * size, size).map(Departments::createEntity)
        return Page(
            content = list,
            totalElements = total,
            totalPages = (total + size - 1) / size,
            size = size
        )

    }

    @CacheEvict("deptList")
    override fun createDept(department: Department): Boolean {

        return database.sequenceOf(Departments).add(department) > 0
    }

    @CacheEvict("deptList")
    override fun updateDept(department: Department): Boolean {
        return database.sequenceOf(Departments).update(department) > 0
    }

    @Transactional
    @CacheEvict("deptList")
    override fun deleteDept(id: Int): Boolean {

        database.sequenceOf(Departments).removeIf { it.id eq id } > 0

        database.sequenceOf(DepartmentMembers).removeIf { it.deptId eq id}

        return true
    }

    override fun setPrimaryHead(deptId: Int, userId: Int?): Boolean {


        return database.update(Departments){
            set(it.primaryHead, userId)
            where {
                it.id eq deptId
            }
        } == 0
    }

    override fun setSecondaryHead(deptId: Int, userId: Int?): Boolean {

        return database.update(Departments){
            set(it.secondaryHead, userId)
            where {
                it.id eq deptId
            }
        } == 0
    }

    override fun changeDeptMembers(deptId: Int, userIds: List<Int>): Boolean {

        database.sequenceOf(DepartmentMembers).removeIf { it.deptId eq deptId }

        database.insert(DepartmentMembers){
            set(it.deptId, deptId)
            set(it.deptMember, userIds)
        }

        return true
    }


}