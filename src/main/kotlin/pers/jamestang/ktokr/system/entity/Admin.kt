package pers.jamestang.ktokr.system.entity

import org.ktorm.entity.Entity


interface Admin : Entity<Admin>{
    companion object : Entity.Factory<Admin>()
    val id: Int
    var username: String
    var password: String
    var email: String

}