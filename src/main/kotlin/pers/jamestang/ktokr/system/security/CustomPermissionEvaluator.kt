package pers.jamestang.ktokr.system.security

import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.springframework.cache.annotation.CachePut
import org.springframework.security.access.PermissionEvaluator
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import pers.jamestang.ktokr.system.repository.Permissions
import pers.jamestang.ktokr.system.repository.RolePermissions
import pers.jamestang.ktokr.system.repository.Roles
import java.io.Serializable

@Component
class CustomPermissionEvaluator(
    private val database: Database
) : PermissionEvaluator {
    override fun hasPermission(authentication: Authentication, targetDomainObject: Any, permission: Any): Boolean {

        return getCurrentRoleSetPermission(authentication.authorities.map { it.authority }).contains(permission.toString())
    }

    override fun hasPermission(
        authentication: Authentication?,
        targetId: Serializable?,
        targetType: String?,
        permission: Any?
    ): Boolean {
        return false
    }


    @CachePut(cacheNames = ["permission"], key = "#roleCodes")
    fun getCurrentRoleSetPermission(roleCodes: List<String>): List<String> {
        return database.from(Permissions)
            .leftJoin(RolePermissions, on = Permissions.id eq RolePermissions.permissionId)
            .leftJoin(Roles, on = RolePermissions.roleId eq Roles.id)
            .select(Permissions.code)
            .where {
                Roles.roleCode inList roleCodes
            }.map { row ->
                row[Permissions.code]!!
            }

    }
}