package pers.jamestang.ktokr.system.repository

import org.ktorm.schema.Table
import org.ktorm.schema.int
import pers.jamestang.ktokr.system.entity.RoleMenu

object RoleMenus: Table<RoleMenu>("role_menus") {
    val roleId = int("role_id").primaryKey().bindTo { it.roleId }
    val menuId = int("menu_id").primaryKey().bindTo { it.menuId }
}