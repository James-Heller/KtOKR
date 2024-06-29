package pers.jamestang.ktokr.system.service.impl

import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.count
import org.ktorm.entity.first
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pers.jamestang.ktokr.system.entity.Admin
import pers.jamestang.ktokr.system.entity.Menu
import pers.jamestang.ktokr.system.entity.Role
import pers.jamestang.ktokr.system.repository.*
import pers.jamestang.ktokr.system.service.IAdminService
import pers.jamestang.ktokr.system.util.Page

@Service
class AdminService(
    private val database: Database
) : IAdminService {
    override fun insertUser(username: String, password: String, email: String): Boolean {
        val result = database.insert(Admins) {
            set(it.username, username)
            set(it.password, password)
            set(it.email, email)
        }

        return result == 1
    }

    override fun deleteUser(id: Int): Boolean {

        return database.delete(Admins) {
            it.id eq id
        } == 1
    }

    override fun updateUser(id: Int, username: String, password: String, email: String): Boolean {

        return database.update(Admins) {
            set(it.username, username)
            set(it.password, password)
            set(it.email, email)
            where {
                it.id eq id
            }
        } == 1
    }

    override fun selectUserById(id: Int): Admin {

        return database.sequenceOf(Admins).first {
            it.id eq id
        }
    }

    override fun getAllUsers(): List<Admin> {

        return database.sequenceOf(Admins).toList()
    }

    override fun pageUsers(page: Int, size: Int): Page<Admin> {

        val totalElements = database.sequenceOf(Admins).count()
        val totalPages = (totalElements + size - 1) / size
        val content = database.from(Admins)
            .select()
            .limit((page - 1) * size, size)
            .map { row ->
                Admins.createEntity(row)
            }
            .toList()

        return Page(content, totalElements, totalPages, size)
    }


    override fun getUserRoles(id: Int): List<Role> {

        val data = database.from(Roles)
            .leftJoin(UserRoles, on = Roles.id eq UserRoles.roleId)
            .select(Roles.columns)
            .where(UserRoles.userId eq id)
            .map { row ->
                Roles.createEntity(row)
            }.toList()

        return data
    }

    @Transactional
    override fun setUserRoles(id: Int, roleIds: List<Int>): Boolean {

        database.delete(UserRoles) {
            it.userId eq id
        }

        val result = database.batchInsert(UserRoles) {
            for (roleId in roleIds) {
                item {
                    set(it.userId, id)
                    set(it.roleId, roleId)
                }
            }
        }

        return result.size == roleIds.size
    }

    override fun getUserMenus(id: Int): List<Menu> {

        return database.from(Menus)
            .leftJoin(RoleMenus, on = RoleMenus.menuId eq Menus.id)
            .leftJoin(UserRoles, on = UserRoles.roleId eq RoleMenus.roleId)
            .select(Menus.columns)
            .where(UserRoles.userId eq id).map(Menus::createEntity)
    }



}