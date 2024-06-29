package pers.jamestang.ktokr.system.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.ktorm.jackson.KtormModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JacksonConfig {

    @Bean
    fun jacksonObjectMapper(): ObjectMapper{
        val objectMapper = ObjectMapper()
        objectMapper.registerModules(KtormModule())

        return objectMapper
    }
}