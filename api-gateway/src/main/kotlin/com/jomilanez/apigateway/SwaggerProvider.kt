package com.jomilanez.apigateway

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.cloud.gateway.config.GatewayProperties
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component
import springfox.documentation.swagger.web.SwaggerResource
import springfox.documentation.swagger.web.SwaggerResourcesProvider

@Component
@Primary
@EnableConfigurationProperties(ApiProperties::class)
class SwaggerProvider(
    val routeLocator: RouteLocator,
    val gatewayProperties: GatewayProperties,
    val apiProperties: ApiProperties
) : SwaggerResourcesProvider {

    override fun get(): MutableList<SwaggerResource> {
        val routes = ArrayList<String>()
        routeLocator.routes.subscribe { routes.add(it.id) }
        return gatewayProperties.routes
            .filter { routes.contains(it.id) }
            .filter { it.id.contains("-api") }
            .map { swaggerResource(it.id.substringBefore("-api")) }
            .toMutableList()
    }

    private fun swaggerResource(name: String): SwaggerResource {
        val swaggerResource = SwaggerResource()
        swaggerResource.name = name
        swaggerResource.location = "${apiProperties.url}/v2/api-docs/$name"
        swaggerResource.swaggerVersion = "2.0"
        return swaggerResource
    }
}
