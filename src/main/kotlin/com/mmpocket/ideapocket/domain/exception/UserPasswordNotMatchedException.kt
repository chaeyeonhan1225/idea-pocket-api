package com.mmpocket.ideapocket.domain.exception

class UserPasswordNotMatchedException(
    override val message: String
): RuntimeException(message)