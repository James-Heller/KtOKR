package pers.jamestang.ktokr.system.repository

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar
import pers.jamestang.ktokr.system.entity.Admin


object Admins: Table<Admin>("system_user") {
    val id = int("id").primaryKey().bindTo { it.id }
    val username = varchar("username").bindTo { it.username }
    val password = varchar("password").bindTo { it.password }
    val email = varchar("email").bindTo { it.email }
}