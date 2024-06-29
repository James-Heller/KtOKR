package pers.jamestang.ktokr.system.service.impl

import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.*
import org.springframework.stereotype.Service
import pers.jamestang.ktokr.system.entity.Permission
import pers.jamestang.ktokr.system.repository.Permissions
import pers.jamestang.ktokr.system.service.IPermissionService
import pers.jamestang.ktokr.system.util.Page

@Service
class PermissionService(
    private val database: Database
): IPermissionService {

    override fun listAll(): List<Permission> {

        return database.sequenceOf(Permissions).toList()
    }

    override fun page(currentPage: Int, size: Int): Page<Permission> {
        val total = database.sequenceOf(Permissions).count()
        val data = database.from(Permissions)
            .select(Permissions.columns)
            .limit((currentPage - 1) * size, size).map(Permissions::createEntity)
        return Page(
            totalElements =  total,
            content = data,
            totalPages = (total + size - 1) / size,
           size = size
        )
    }

    override fun create(permission: Permission): Boolean {
        return database.sequenceOf(Permissions).add(permission) == 1
    }

    override fun update(permission: Permission): Boolean {
        return database.sequenceOf(Permissions).update(permission) == 1
    }

    override fun delete(id: Int): Boolean {
        return database.sequenceOf(Permissions).removeIf { it.id eq id } == 1
    }
}