package com.jomilanez.apigateway

import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.authentication.HttpStatusServerEntryPoint

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
class SecurityConfig {

    @Bean
    fun springWebFilterChain(
        http: ServerHttpSecurity,
        securityContextRepository: SecurityContextRepository,
        authenticationManager: ApiAuthenticationManager
    ): SecurityWebFilterChain? =
        http
            .authorizeExchange()
            .pathMatchers(HttpMethod.OPTIONS).permitAll()
            .pathMatchers(
                "/swagger-ui.html**",
                "/v2/api-docs/**",
                "/webjars/**",
                "/swagger-resources/**",
                "**/springfox-swagger-ui/**",
                "favicon.ico",
                "/csrf**",
                "/"
            )
            .permitAll()
            .anyExchange()
            .authenticated()
            .and()
            .csrf()
            .disable()
            .exceptionHandling()
            .authenticationEntryPoint(HttpStatusServerEntryPoint(HttpStatus.UNAUTHORIZED))
            .and()
            .securityContextRepository(securityContextRepository)
            .build()

}
