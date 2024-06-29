package pers.jamestang.ktokr.system.entity

import org.ktorm.entity.Entity

interface RolePermission: Entity<RolePermission> {
    companion object: Entity.Factory<RolePermission>()
    val roleId: Int
    val permissionId: Int
}