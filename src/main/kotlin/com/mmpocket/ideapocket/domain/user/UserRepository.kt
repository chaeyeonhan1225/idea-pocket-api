package com.mmpocket.ideapocket.domain.user

import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository: JpaRepository<User, UserId> {
    fun findByEmail(email: String): Optional<User>
}