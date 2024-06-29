package pers.jamestang.ktokr.system.security

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
class UnAuthorizeHandler: AuthenticationEntryPoint {

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {

        response.contentType = "application/json;charset=utf-8"
        response.status = HttpServletResponse.SC_OK

        response.writer.write(
            """
            {
                "code": 401,
                "message": "Unauthorized. You need to login first."
            }
            """.trimIndent()
        )
    }
}