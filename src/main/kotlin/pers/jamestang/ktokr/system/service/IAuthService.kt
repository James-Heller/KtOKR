package pers.jamestang.ktokr.system.service

import pers.jamestang.ktokr.system.entity.Menu

interface IAuthService {

    /**
     * Login by password.
     * @param username the username
     * @param password the password
     * @return the token
     */
    fun loginByPassword(username: String, password: String): String


    fun registry(username: String, password: String, email: String): Boolean


    fun getCurrentUserMenus(): List<Menu>

    fun getCurrentUserPermissions(): List<String?>
}