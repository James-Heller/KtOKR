package pers.jamestang.ktokr.okr.service

import pers.jamestang.ktokr.okr.entity.vo.OKR

interface IOKRService {

    fun getList(): List<OKR>

    fun getByUserId(adminId: Int): List<OKR>

}