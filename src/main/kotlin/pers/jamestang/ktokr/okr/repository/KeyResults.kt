package pers.jamestang.ktokr.okr.repository

import org.ktorm.schema.*
import pers.jamestang.ktokr.okr.entity.KeyResult
import pers.jamestang.ktokr.okr.entity.KeyResultStatus
import pers.jamestang.ktokr.okr.entity.MeasurementType

object KeyResults: Table<KeyResult>("key_results") {

    val id = int("key_result_id").primaryKey().bindTo { it.id }
    val objectiveId = int("objective_id").bindTo { it.objectiveId }
    var title = varchar("title").bindTo { it.title }
    var description = varchar("description").bindTo { it.description }
    var targetValue = decimal("target_value").bindTo { it.targetValue }
    var measurementType = enum<MeasurementType>("measurement_type").bindTo { it.measurementType }
    var status = enum<KeyResultStatus>("status").bindTo { it.status }

}