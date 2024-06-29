package pers.jamestang.ktokr.system.controller

import org.springframework.web.bind.annotation.*
import pers.jamestang.ktokr.system.entity.Permission
import pers.jamestang.ktokr.system.service.IPermissionService
import pers.jamestang.ktokr.system.util.Resp

@RestController
@RequestMapping("/permission")
class PermissionController(
    private val permissionService: IPermissionService
) {

    @GetMapping("/list")
    fun list() = Resp.data(permissionService.listAll())

    @GetMapping("/page")
    fun page(currentPage: Int, size: Int) = Resp.data(permissionService.page(currentPage, size))

    @PostMapping("/create")
    fun create(@RequestBody permission: Permission) = if (permissionService.create(permission)){
        Resp.success()
    }else Resp.error("Create permission failed")

    @PutMapping("/update")
    fun update(@RequestBody permission: Permission) = if (permissionService.update(permission)){
        Resp.success()
    }else Resp.error("Update permission failed")


    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable id: Int) = if (permissionService.delete(id)){
        Resp.success()
    }else Resp.error("Delete permission failed")



}