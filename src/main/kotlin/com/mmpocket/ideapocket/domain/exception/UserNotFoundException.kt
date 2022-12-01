package com.mmpocket.ideapocket.domain.exception

class UserNotFoundException(
    override val message: String
): RuntimeException(message)