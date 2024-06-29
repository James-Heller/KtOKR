package pers.jamestang.ktokr.system.security.filter

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import pers.jamestang.ktokr.system.security.DBAuthHandler
import pers.jamestang.ktokr.system.util.JWTUtil
import pers.jamestang.ktokr.system.util.Resp

@Component
class JwtAuthenticationFilter(
    private val dbAuthHandler: DBAuthHandler,
    private val objectMapper: ObjectMapper
): OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val authHeader = request.getHeader("Authorization")
        if (authHeader.isNullOrBlank() || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return
        }

        if (
            request.requestURI == "/auth/login" ||
            request.requestURI == "/auth/wechatLogin" ||
            request.requestURI == "/auth/register"
        ) {
            filterChain.doFilter(request, response)
            return
        }



        val token = authHeader.substring(7)

        if (!JWTUtil.validateToken(token)) {
            response.writer.write(
                objectMapper.writeValueAsString(Resp.error(4396, "your token is invalid"))
            )
            return
        }

        val username = JWTUtil.getSubject(token)


        if (SecurityContextHolder.getContext().authentication == null) {

            val userDetails = this.dbAuthHandler.loadUserByUsername(username)
            val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
            authentication.details = WebAuthenticationDetailsSource().buildDetails(request)

            SecurityContextHolder.getContext().authentication = authentication

            filterChain.doFilter(request, response)
        }


    }

}