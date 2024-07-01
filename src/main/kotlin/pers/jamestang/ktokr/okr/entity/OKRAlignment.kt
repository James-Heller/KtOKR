package pers.jamestang.ktokr.okr.entity

import org.ktorm.entity.Entity

interface OKRAlignment : Entity<OKRAlignment>{
    companion object : Entity.Factory<OKRAlignment>()
    var id: Int
    var masterId: Int
    var slaveId: Int

}