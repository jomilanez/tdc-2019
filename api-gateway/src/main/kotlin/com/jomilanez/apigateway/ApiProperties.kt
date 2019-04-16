package com.jomilanez.apigateway

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("api")
class ApiProperties(var url: String = "")
