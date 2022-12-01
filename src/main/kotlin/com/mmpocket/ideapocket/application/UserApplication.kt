package com.mmpocket.ideapocket.application

import com.mmpocket.ideapocket.domain.user.User
import com.mmpocket.ideapocket.domain.user.UserId
import com.mmpocket.ideapocket.domain.user.UserParam
import com.mmpocket.ideapocket.domain.user.UserRepository
import com.mmpocket.ideapocket.domain.service.SequenceGenerator
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserApplication(
    private val repository: UserRepository,
    private val sequenceGenerator: SequenceGenerator
) {

    fun createUser(param: UserParam): User {
        val userId = UserId(sequenceGenerator.generate(User::class.java.simpleName))
        val user = User(id = userId, param = param)
        println("user sign up!")
        return repository.save(user)
    }
}