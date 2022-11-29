package com.mmpocket.ideapocket.application

import com.mmpocket.ideapocket.domain.user.User
import com.mmpocket.ideapocket.domain.user.UserId
import com.mmpocket.ideapocket.domain.user.UserRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserProvider(
    private val repository: UserRepository
){
    fun findAll(): List<User> {
        return repository.findAll()
    }

    fun findById(id: String): User {
        val userId = UserId(id.toLong())
        return repository.findById(userId).orElseThrow { NotFoundException() }
    }

    fun findByEmail(email: String): User {
        return repository.findByEmail(email).orElseThrow { NotFoundException() }
    }
}