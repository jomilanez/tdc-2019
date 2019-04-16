package com.jomilanez.contentapi

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@EnableSwagger2
@Configuration
@EnableConfigurationProperties(SwaggerProperties::class)
class SwaggerConfiguration(private val swaggerProperties: SwaggerProperties) {

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .host(swaggerProperties.host)
            .protocols(setOf(swaggerProperties.protocol))
            .produces(setOf(MediaType.APPLICATION_JSON_VALUE))
            .apiInfo(apiInfo())
            .useDefaultResponseMessages(false)
            .produces(setOf("application/json"))
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.jomilanez.contentapi"))
            .paths(PathSelectors.any())
            .build()
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
            .title("Content API")
            .description("Content API")
            .termsOfServiceUrl("")
            .contact(Contact("TDC 2019", "", ""))
            .version("1.0")
            .build()
    }

}
