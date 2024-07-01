package pers.jamestang.ktokr

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pers.jamestang.ktokr.okr.service.impl.OKRService

@SpringBootTest
class SpringRbacApplicationTests {

    @Autowired
    lateinit var okrService: OKRService

    @Test
    fun contextLoads() {

        okrService.getList().forEach(::println)
    }

}
