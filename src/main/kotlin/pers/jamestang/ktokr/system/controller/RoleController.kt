package pers.jamestang.ktokr.system.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pers.jamestang.ktokr.system.entity.Role
import pers.jamestang.ktokr.system.service.IRoleService
import pers.jamestang.ktokr.system.util.Resp

@RestController
@RequestMapping("/role")
class RoleController(
    private val roleService: IRoleService
) {

    @GetMapping("/list")
    fun list() = Resp.data("roleService.list()")

    @GetMapping("/page")
    fun page(currentPage: Int, size: Int) = Resp.data(roleService.pageRole(currentPage, size))

    @PostMapping("/create")
    fun create(@RequestBody role: Role) = if (roleService.createRole(role)){
        Resp.success()
    }else Resp.error("Create role failed")

    @GetMapping("/get")
    fun get(id: Int) = Resp.data(roleService.getRoleById(id))

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable id: Int) = if (roleService.deleteRole(id)){
        Resp.success()
    }else Resp.error("Delete role failed")

    @PutMapping("/update")
    fun update(@RequestBody role: Role) = if (roleService.updateRole(role)){
        Resp.success()
    }else Resp.error("Update role failed")
}