package com.mmpocket.ideapocket.domain.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class UserPasswordNotMatchedException(
    override val message: String
): ResponseStatusException(
    HttpStatus.BAD_REQUEST,
    message
)