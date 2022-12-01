package com.mmpocket.ideapocket.domain.exception

class UserDisabledException(
    override val message: String
): RuntimeException(message)