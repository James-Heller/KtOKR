package pers.jamestang.ktokr.system.entity

import org.ktorm.entity.Entity

interface Role: Entity<Role> {
    companion object : Entity.Factory<Role>()
    val id: Int
    var roleCode: String
    var roleName: String
}