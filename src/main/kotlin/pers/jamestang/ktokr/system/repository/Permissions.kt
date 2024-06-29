package pers.jamestang.ktokr.system.repository

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar
import pers.jamestang.ktokr.system.entity.Permission

object Permissions: Table<Permission>("system_permission") {
    val id = int("id").primaryKey().bindTo { it.id }
    val code = varchar("code").bindTo { it.code }
    val name = varchar("name").bindTo { it.name }
}