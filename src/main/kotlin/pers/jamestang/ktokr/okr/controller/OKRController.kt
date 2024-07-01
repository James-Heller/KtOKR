package pers.jamestang.ktokr.okr.controller

import org.springframework.web.bind.annotation.*
import pers.jamestang.ktokr.okr.entity.vo.OKR
import pers.jamestang.ktokr.okr.service.IOKRService
import pers.jamestang.ktokr.system.util.Resp

@RestController
@RequestMapping("/okr")
class OKRController(
    private val okrService: IOKRService
) {


    @GetMapping("/list")
    fun list() = Resp.data(okrService.getList())

    @GetMapping("/getByUserId")
    fun user(@RequestParam adminId: Int): Resp {

        val list = okrService.getByUserId(adminId)


        return Resp.data(list)
    }

    @PostMapping("/create")
    fun create(@RequestBody okr: OKR): Resp {
        val result = okrService.createOKR(okr)
        if (result) {
            return Resp.success()
        }
        return Resp.error("创建失败")
    }

    @PostMapping("/align")
    fun align(@RequestBody okr: OKR, @RequestParam masterObjectiveId: Int): Resp {
        val result = okrService.createAlignOKR(okr, masterObjectiveId)
        if (result) {
            return Resp.success()
        }
        return Resp.error("创建失败")
    }
}