package pers.jamestang.ktokr.okr.entity

import org.ktorm.entity.Entity
import java.math.BigDecimal

interface KeyResult: Entity<KeyResult> {
    companion object: Entity.Factory<KeyResult>()
    val id : Int
    var objectiveId: Int
    var title: String
    var description: String?
    var targetValue: BigDecimal
    var currentValue: BigDecimal
    var measurementType: MeasurementType
    var status: KeyResultStatus

}

enum class MeasurementType { NUMBER, PERCENTAGE}

enum class KeyResultStatus { NOT_STARTED,IN_PROGRESS, ACHIEVED,MISSED}