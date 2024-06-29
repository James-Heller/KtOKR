package pers.jamestang.ktokr.system.service

import pers.jamestang.ktokr.system.entity.Menu

interface IMenuService {

    fun listMenu(): List<Menu>

    fun addMenu(menu: Menu): Boolean

    fun updateMenu(menu: Menu): Boolean

    fun deleteMenu(menuId: Int): Boolean
}