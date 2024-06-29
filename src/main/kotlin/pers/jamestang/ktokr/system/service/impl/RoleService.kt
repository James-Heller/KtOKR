package pers.jamestang.ktokr.system.service.impl

import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pers.jamestang.ktokr.system.entity.Menu
import pers.jamestang.ktokr.system.entity.Permission
import pers.jamestang.ktokr.system.entity.Role
import pers.jamestang.ktokr.system.repository.*
import pers.jamestang.ktokr.system.service.IRoleService
import pers.jamestang.ktokr.system.util.Page

@Service
class RoleService(
    private val database: Database
) : IRoleService {

    override fun getRoleById(id: Int): Role {
        return database.sequenceOf(Roles).find { it.id eq id } ?: throw Exception("Role not found")
    }

    override fun pageRole(currentPage: Int, size: Int): Page<Role> {
        val total = database.sequenceOf(Roles).count()
        val roles = database.from(Roles).select().limit((currentPage - 1) * size, size).map { Roles.createEntity(it) }
        return Page(roles, total, total / size + 1, size)
    }

    override fun list(): List<Role> {

        return database.sequenceOf(Roles).toList()
    }

    override fun createRole(role: Role): Boolean {

        val result = database.sequenceOf(Roles).add(role)
        return result == 1
    }

    override fun updateRole(role: Role): Boolean {

        val result = database.sequenceOf(Roles).update(role)
        return result == 1
    }

    @Transactional
    override fun deleteRole(id: Int): Boolean {
        val deleteRoleFlag = database.sequenceOf(Roles).removeIf { it.id eq id } == 1

        if (deleteRoleFlag) {
            database.delete(UserRoles) {
                it.roleId eq id
            }
        }
        return deleteRoleFlag
    }

    override fun getRoleMenus(roleId: Int): List<Menu> {

        return database.from(Menus)
            .rightJoin(RoleMenus, Menus.id eq RoleMenus.menuId)
            .selectDistinct(Menus.columns)
            .where { RoleMenus.roleId eq roleId }
            .map(Menus::createEntity)

    }

    @Transactional
    override fun setRoleMenus(roleId: Int, menuIds: List<Int>): Boolean {

        database.delete(RoleMenus) {
            it.roleId eq roleId
        }

        val result = database.batchInsert(RoleMenus) {
            for (menuId in menuIds) {
                item {
                    it.roleId to roleId
                    it.menuId to menuId
                }
            }
        }

        return result.size == menuIds.size
    }

    override fun getRolePermissions(roleId: Int): List<Permission> {
        return database.from(Permissions)
            .rightJoin(RolePermissions, Permissions.id eq RolePermissions.permissionId)
            .selectDistinct(Permissions.columns)
            .where { RolePermissions.roleId eq roleId }
            .map(Permissions::createEntity)
    }

    @Transactional
    override fun setRolePermissions(roleId: Int, permissionIds: List<Int>): Boolean {
        database.delete(RolePermissions) {
            it.roleId eq roleId
        }
        val result = database.batchInsert(RolePermissions) {
            for (permissionId in permissionIds) {
                item {
                    it.roleId to roleId
                    it.permissionId to permissionId
                }
            }
        }
        return result.size == permissionIds.size
    }
}