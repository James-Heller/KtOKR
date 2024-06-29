package pers.jamestang.ktokr.system.security

import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import pers.jamestang.ktokr.system.entity.LoginAdmin
import pers.jamestang.ktokr.system.repository.Admins
import pers.jamestang.ktokr.system.repository.Roles
import pers.jamestang.ktokr.system.repository.UserRoles

@Component
class DBAuthHandler(
    private val database: Database
): UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {

        val admin = database.from(Admins)
            .select()
            .where { Admins.username eq username }
            .map { Admins.createEntity(it) }
            .firstOrNull() ?: throw Exception("Admin not found")

        val userRoles = database.from(Roles)
            .leftJoin(UserRoles, Roles.id eq UserRoles.roleId)
            .select(Roles.roleCode)
            .map { row -> SimpleGrantedAuthority(row[Roles.roleCode]) }

        return LoginAdmin(admin, userRoles.toMutableList())
    }
}