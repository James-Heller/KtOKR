package pers.jamestang.ktokr.okr.service

import pers.jamestang.ktokr.okr.entity.KeyResult
import pers.jamestang.ktokr.okr.entity.vo.OKR

interface IOKRService {

    fun getList(): List<OKR>

    fun getByUserId(adminId: Int): List<OKR>

    fun createOKR(okr: OKR): Boolean

    fun createAlignOKR(okr: OKR, masterObjectiveId: Int): Boolean

    fun getCanAlignOKRList(): List<KeyResult>

    fun removeInvisibleOKR(list: List<OKR>): List<OKR>

}