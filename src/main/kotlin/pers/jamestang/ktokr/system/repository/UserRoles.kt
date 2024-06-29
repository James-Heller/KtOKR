package pers.jamestang.ktokr.system.repository

import org.ktorm.schema.Table
import org.ktorm.schema.int
import pers.jamestang.ktokr.system.entity.UserRole

object UserRoles: Table<UserRole>("user_roles") {
    val userId = int("user_id").primaryKey().bindTo { it.userId }
    val roleId = int("role_id").primaryKey().bindTo { it.roleId }
}