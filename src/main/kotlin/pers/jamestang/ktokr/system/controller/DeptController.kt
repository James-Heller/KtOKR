package pers.jamestang.ktokr.system.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pers.jamestang.ktokr.system.entity.Department
import pers.jamestang.ktokr.system.service.impl.DeptService
import pers.jamestang.ktokr.system.util.Resp

@RestController
@RequestMapping("/dept")
class DeptController(
    private val deptService: DeptService
) {

    @GetMapping("/list")
    fun list() = Resp.data(deptService.getList())

    @GetMapping("/pageList")
    fun pageList(page: Int, size: Int) = Resp.data(deptService.getPageList(page, size))

    @PostMapping("/create")
    fun create(@RequestBody dept: Department) = if (deptService.createDept(dept)){
        Resp.success()
    } else {
        Resp.error("创建失败")
    }

    @PostMapping("/update")
    fun update(@RequestBody dept: Department) = if (deptService.updateDept(dept)){
        Resp.success()
    } else {
        Resp.error("更新失败")
    }

    @PutMapping("/delete")
    fun delete(id: Int) = if (deptService.deleteDept(id)){
        Resp.success()
    } else {
        Resp.error("删除失败")
    }

    @PutMapping("/setPrimaryHead")
    fun setPrimaryHead(deptId: Int, userId: Int?) = if (deptService.setPrimaryHead(deptId, userId)){
        Resp.success()
    } else {
        Resp.error("设置失败")
    }

    @PutMapping("/setSecondaryHead")
    fun setSecondaryHead(deptId: Int, userId: Int?) = if (deptService.setSecondaryHead(deptId, userId)){
        Resp.success()
    } else {
        Resp.error("设置失败")
    }

}