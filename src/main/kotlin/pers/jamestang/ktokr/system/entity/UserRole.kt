package pers.jamestang.ktokr.system.entity

import org.ktorm.entity.Entity

interface UserRole: Entity<UserRole> {
    companion object: Entity.Factory<UserRole>()
    val userId: Int
    val roleId: Int
}