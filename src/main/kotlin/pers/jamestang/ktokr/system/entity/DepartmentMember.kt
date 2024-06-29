package pers.jamestang.ktokr.system.entity

import org.ktorm.entity.Entity

interface DepartmentMember: Entity<DepartmentMember> {
    companion object : Entity.Factory<DepartmentMember>()
    val deptId: Int
    var deptMember: List<Int>

}