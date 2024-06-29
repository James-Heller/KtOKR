package pers.jamestang.ktokr.system.entity

import org.ktorm.entity.Entity

interface Department: Entity<Department> {
    companion object : Entity.Factory<Department>()
    val id: Int
    var name: String
    var parentId: Int?
    var primaryHead: Int?
    var secondaryHead: Int?

}