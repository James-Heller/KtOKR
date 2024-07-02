package pers.jamestang.ktokr.system.service

import pers.jamestang.ktokr.system.entity.Department
import pers.jamestang.ktokr.system.util.Page

interface IDeptService {

    fun getList(): List<Department>

    fun getPageList(page: Int, size: Int): Page<Department>

    fun createDept(department: Department): Boolean

    fun updateDept(department: Department): Boolean

    fun deleteDept(id: Int): Boolean

    fun setPrimaryHead(deptId: Int, userId: Int?): Boolean

    fun setSecondaryHead(deptId: Int, userId: Int?): Boolean

    fun changeDeptMembers(deptId: Int, userIds: List<Int>): Boolean

}