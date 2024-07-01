package pers.jamestang.ktokr.okr.repository

import org.ktorm.schema.*
import pers.jamestang.ktokr.okr.entity.Objective
import pers.jamestang.ktokr.okr.entity.ObjectiveStatus
import pers.jamestang.ktokr.system.repository.Admins
import pers.jamestang.ktokr.system.repository.Departments

object Objectives : Table<Objective>("objective") {
    val id = int("objective_id").primaryKey().bindTo { it.id }
    val adminId = int("admin_id").bindTo { it.adminId }
    val deptId = int("dept_id").bindTo { it.deptId }
    val parentId = int("parent_id").bindTo { it.parentId }
    val title = varchar("title").bindTo { it.title }
    val description = text("description").bindTo { it.description }
    val startDate = date("start_date").bindTo { it.startDate }
    val endDate = date("end_date").bindTo { it.endDate }
    val status = enum<ObjectiveStatus>("status").bindTo { it.status }
}