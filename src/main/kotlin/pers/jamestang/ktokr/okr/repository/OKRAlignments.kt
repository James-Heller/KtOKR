package pers.jamestang.ktokr.okr.repository

import org.ktorm.schema.Table
import org.ktorm.schema.int
import pers.jamestang.ktokr.okr.entity.OKRAlignment

object OKRAlignments: Table<OKRAlignment>("okr_alignment") {
    val id = int("id").primaryKey().bindTo { it.id }
    val masterId = int("master_id").bindTo { it.masterId }
    val slaveId = int("slave_id").bindTo { it.slaveId }
}