package pers.jamestang.ktokr.okr.entity

import org.ktorm.entity.Entity
import pers.jamestang.ktokr.system.entity.Admin
import pers.jamestang.ktokr.system.entity.Department
import java.time.LocalDate


interface Objective : Entity<Objective> {
    companion object : Entity.Factory<Objective>()

    val id: Int
    var adminId: Int
    var deptId: Int?
    var parentId: Int?
    var title: String
    var description: String?
    var startDate: LocalDate
    var endDate: LocalDate
    var status: ObjectiveStatus

}

enum class ObjectiveStatus { DRAFT, IN_PROGRESS, COMPLETED, CANCELLED }

