package pers.jamestang.ktokr.system.repository

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar
import pers.jamestang.ktokr.system.entity.Menu

object Menus: Table<Menu>("system_menu") {
    val id = int("id").primaryKey().bindTo { it.id }
    val parentId = int("parent_id").bindTo { it.parentId }
    val name = varchar("name").bindTo { it.name }
    val url = varchar("url").bindTo { it.url }
    val icon = varchar("icon").bindTo { it.icon }
    val orderNum = int("order_num").bindTo { it.orderNum }
    val component = varchar("component").bindTo { it.component }

}