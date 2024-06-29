package pers.jamestang.ktokr

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class SpringRbacApplication

fun main(args: Array<String>) {
    runApplication<SpringRbacApplication>(*args)
}
