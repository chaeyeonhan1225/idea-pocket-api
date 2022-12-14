package com.mmpocket.ideapocket.domain.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class UserDisabledException(
    override val message: String
): ResponseStatusException(
    HttpStatus.NOT_FOUND,
    message
)