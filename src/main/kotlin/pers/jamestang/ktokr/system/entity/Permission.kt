package pers.jamestang.ktokr.system.entity

import org.ktorm.entity.Entity

interface Permission: Entity<Permission> {
    companion object: Entity.Factory<Permission>()
    val id: Int
    var code: String
    var name: String
}