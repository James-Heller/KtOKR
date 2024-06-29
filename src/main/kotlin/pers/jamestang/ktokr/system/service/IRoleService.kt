package pers.jamestang.ktokr.system.service


import pers.jamestang.ktokr.system.entity.Menu
import pers.jamestang.ktokr.system.entity.Permission
import pers.jamestang.ktokr.system.entity.Role
import pers.jamestang.ktokr.system.util.Page

interface IRoleService {

    fun getRoleById(id: Int): Role

    fun pageRole(currentPage: Int, size: Int): Page<Role>

    fun list(): List<Role>

    fun createRole(role: Role): Boolean

    fun updateRole(role: Role): Boolean

    fun deleteRole(id: Int): Boolean

    fun getRoleMenus(roleId: Int): List<Menu>

    fun setRoleMenus(roleId: Int, menuIds: List<Int>):Boolean

    fun getRolePermissions(roleId: Int): List<Permission>

    fun setRolePermissions(roleId: Int, permissionIds: List<Int>): Boolean
}