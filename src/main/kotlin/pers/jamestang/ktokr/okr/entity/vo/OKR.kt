package pers.jamestang.ktokr.okr.entity.vo

import pers.jamestang.ktokr.okr.entity.KeyResult
import pers.jamestang.ktokr.okr.entity.Objective

data class OKR(
    val objective: Objective,
    val keyResults: List<KeyResult>
)