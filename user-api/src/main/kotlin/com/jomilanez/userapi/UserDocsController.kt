package com.jomilanez.userapi

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v2/api-docs")
class UserDocsController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getUser(): UserDto = UserDto(userId = "id", country = "BR", language = "pt")
}
