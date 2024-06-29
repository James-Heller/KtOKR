package pers.jamestang.ktokr.system.controller

import org.springframework.web.bind.annotation.*
import pers.jamestang.ktokr.system.service.IAdminService
import pers.jamestang.ktokr.system.util.Resp

@RestController
@RequestMapping("/admin")
class AdminController(
    private val adminService: IAdminService
) {

    @GetMapping("/page")
    fun getPage(currentPage: Long, size: Long) = Resp.data(adminService.pageUsers(currentPage.toInt(), size.toInt()))

    @GetMapping("/list")
    fun list() = Resp.data(adminService.getAllUsers())

    @GetMapping("/get")
    fun get(id: Int) = Resp.data(adminService.selectUserById(id))

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable id: Int) = Resp.data(adminService.deleteUser(id))

    @GetMapping("/getUserRoles")
    fun getUserRole(id: Int) = Resp.data(adminService.getUserRoles(id))

    @PostMapping("/updateUserRole")
    fun updateUserRole(userId: Int, roleIds: List<Int>) = Resp.data(adminService.setUserRoles(userId, roleIds))

    @GetMapping("/getUserMenus")
    fun getUserMenus(id: Int) = Resp.data(adminService.getUserMenus(id))

}