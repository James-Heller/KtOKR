package pers.jamestang.ktokr.system.repository

import org.ktorm.jackson.json
import org.ktorm.schema.Table
import org.ktorm.schema.int
import pers.jamestang.ktokr.system.entity.DepartmentMember

object DepartmentMembers: Table<DepartmentMember>("dept_members") {
    val deptId = int("dept_id").primaryKey().bindTo { it.deptId }
    val deptMember = json<List<Int>>("dept_member").bindTo { it.deptMember }
}