package pers.jamestang.ktokr.system.service

import pers.jamestang.ktokr.system.entity.Permission
import pers.jamestang.ktokr.system.util.Page

interface IPermissionService {

    fun listAll(): List<Permission>

    fun page(currentPage: Int, size: Int): Page<Permission>

    fun create(permission: Permission): Boolean

    fun update(permission: Permission): Boolean

    fun delete(id: Int): Boolean
}