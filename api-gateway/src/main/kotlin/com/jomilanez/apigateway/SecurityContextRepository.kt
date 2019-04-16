package com.jomilanez.apigateway

import org.springframework.security.access.intercept.RunAsUserToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextImpl
import org.springframework.security.web.server.context.ServerSecurityContextRepository
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

const val API_KEY_HEADER = "api-key"

@Component
class SecurityContextRepository(val authenticationManager: ApiAuthenticationManager) : ServerSecurityContextRepository {

    override fun save(swe: ServerWebExchange, sc: SecurityContext): Mono<Void> {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun load(swe: ServerWebExchange): Mono<SecurityContext> {
        return swe.request.headers[API_KEY_HEADER]?.let {
            authenticationManager
                .authenticate(getAuthentication(it[0]))
                .map { securityContextImpl(it) }
        } ?: Mono.empty()
    }

    private fun securityContextImpl(it: Authentication?): SecurityContext = SecurityContextImpl(it)

    private fun getAuthentication(apiSecret: String) =
        RunAsUserToken(API_KEY_HEADER, apiSecret, null, null, null)
}
