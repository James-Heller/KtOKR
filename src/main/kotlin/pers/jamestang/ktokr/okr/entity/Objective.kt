package pers.jamestang.ktokr.okr.entity

import org.ktorm.entity.Entity
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
    var scope: Scope
    var status: ObjectiveStatus
    var visibility: OKRVisibility

}

enum class ObjectiveStatus { DRAFT, IN_PROGRESS, COMPLETED, CANCELLED }
enum class Scope { COMPANY, DEPARTMENT, PERSONAL }
enum class OKRVisibility{ PUBLIC, LEADER_ONLY}

