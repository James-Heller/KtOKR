package pers.jamestang.ktokr.system.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import pers.jamestang.ktokr.system.security.CustomPermissionEvaluator
import pers.jamestang.ktokr.system.security.RestAccessDeniedHandler
import pers.jamestang.ktokr.system.security.DBAuthHandler
import pers.jamestang.ktokr.system.security.UnAuthorizeHandler
import pers.jamestang.ktokr.system.security.filter.JwtAuthenticationFilter

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
class SecurityConfig(
    private val dbAuthHandler: DBAuthHandler,
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val restAccessDeniedHandler: RestAccessDeniedHandler,
    private val unAuthorizeHandler: UnAuthorizeHandler,
    private val customPermissionEvaluator: CustomPermissionEvaluator
) {

    @Bean
    fun defaultSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {

        http{

            authorizeRequests {
                authorize("/auth/login", permitAll)
                authorize("/auth/registry", permitAll)
                authorize(anyRequest, authenticated)
            }

            formLogin { disable() }
            csrf { disable() }
            sessionManagement { sessionCreationPolicy = SessionCreationPolicy.STATELESS }
            addFilterBefore<UsernamePasswordAuthenticationFilter>(jwtAuthenticationFilter)
            exceptionHandling {
                accessDeniedHandler = restAccessDeniedHandler
                authenticationEntryPoint = unAuthorizeHandler
            }

        }


        return http.build()
    }

    @Bean
    fun injectAuthenticationManager(authenticationConfiguration: AuthenticationConfiguration): ProviderManager {

        val authenticationFunctionList = listOf(
            daoAuthenticationProvider()
        )

        return ProviderManager(authenticationFunctionList)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun createExpressionHandler(): MethodSecurityExpressionHandler{
        val expressHandler = DefaultMethodSecurityExpressionHandler()
        expressHandler.setPermissionEvaluator(customPermissionEvaluator)
        return expressHandler
    }
    
    fun daoAuthenticationProvider(): DaoAuthenticationProvider {
        val daoAuthenticationProvider = DaoAuthenticationProvider()
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder())
        daoAuthenticationProvider.setUserDetailsService(dbAuthHandler)
        return daoAuthenticationProvider
    }
}