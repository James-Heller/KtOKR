package pers.jamestang.ktokr.system.security

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component

@Component
class RestAccessDeniedHandler: AccessDeniedHandler {


    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        response.status = HttpServletResponse.SC_OK
        response.contentType = "application/json;charset=utf-8"
        response.writer.write("""
            {
                "code": 403,
                "message": "Access denied. You don't have permission to access this resource."
            }
        """.trimIndent())
    }
}