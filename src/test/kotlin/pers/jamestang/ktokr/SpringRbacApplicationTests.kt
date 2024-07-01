package pers.jamestang.ktokr

import com.mysql.cj.x.protobuf.Mysqlx.Ok
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pers.jamestang.ktokr.okr.entity.*
import pers.jamestang.ktokr.okr.entity.vo.OKR
import pers.jamestang.ktokr.okr.service.impl.OKRService
import java.math.BigDecimal
import java.time.LocalDate

@SpringBootTest
class SpringRbacApplicationTests {

    @Autowired
    lateinit var okrService: OKRService

    @Test
    fun contextLoads() {

//        val okr = OKR(
//            objective = Objective {
//                title = "Objective 1"
//                adminId = 1
//                deptId = 1
//                parentId = null
//                description = "Objective 1 description"
//                startDate = LocalDate.now()
//                endDate = LocalDate.now().plusDays(30)
//                scope = Scope.COMPANY
//                status = ObjectiveStatus.DRAFT
//
//
//            },
//            keyResults = listOf(
//                KeyResult {
//                    title = "Key Result 1"
//                    description = "Key Result 1 description"
//                    targetValue = BigDecimal(100)
//                    measurementType = MeasurementType.PERCENTAGE
//                    currentValue = BigDecimal.ZERO
//                },
//                KeyResult {
//                    title = "Key Result 2"
//                    description = "Key Result 2 description"
//                    targetValue = BigDecimal(200)
//                    measurementType = MeasurementType.NUMERIC
//                    currentValue = BigDecimal(75)
//                })
//
//        )
//
//        okrService.createOKR(okr)
//        println(okr)
        val list = okrService.getList()
        okrService.removeInvisibleOKR(list).forEach(::println)
    }

}
