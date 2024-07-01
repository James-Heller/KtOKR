package pers.jamestang.ktokr.system.exception

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import pers.jamestang.ktokr.system.util.Resp

@RestControllerAdvice
class GenericExceptionHandler(
    private val objectMapper: ObjectMapper
) {

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): String {
        return objectMapper.writeValueAsString(Resp.error(e.message ?: "Unknown error"))
    }
}