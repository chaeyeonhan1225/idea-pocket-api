package com.mmpocket.ideapocket.domain.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserRepository: JpaRepository<User, UserId> {
    fun findByEmail(email: String): Optional<User>
}