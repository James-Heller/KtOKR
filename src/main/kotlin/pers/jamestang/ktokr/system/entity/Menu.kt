package pers.jamestang.ktokr.system.entity

import org.ktorm.entity.Entity

interface Menu: Entity<Menu> {
    companion object: Entity.Factory<Menu>()
    val id: Int
    var parentId: Int
    var name: String
    var url: String
    var icon: String
    var orderNum: Int
    var component: String
}