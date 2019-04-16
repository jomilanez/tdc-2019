package com.jomilanez.apigateway

import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

const val API_KEY_SECRET = "api-secret"

@Component
class ApiAuthenticationManager : ReactiveAuthenticationManager {

    /**
     * Successfully authenticate an Authentication object
     *
     * @param authentication A valid authentication object
     * @return authentication A valid authentication object
     */
    override fun authenticate(authentication: Authentication): Mono<Authentication> {
        return if (authentication.principal == API_KEY_SECRET)
            Mono.just(authentication)
        else Mono.empty()
    }
}
