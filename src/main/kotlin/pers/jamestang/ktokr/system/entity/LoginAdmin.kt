package pers.jamestang.ktokr.system.entity

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class LoginAdmin(private val admin: Admin, private val authorities: MutableCollection<GrantedAuthority>, private val userDept: Department): UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return authorities
    }

    override fun getPassword(): String {
        return admin.password
    }

    override fun getUsername(): String {
        return admin.username
    }

    fun getUserDept(): Department {
        return userDept
    }

    fun getUserId(): Int {
        return admin.id
    }
}