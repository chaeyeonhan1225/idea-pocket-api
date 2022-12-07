package com.mmpocket.ideapocket.application

import com.mmpocket.ideapocket.domain.exception.UserAlreadyExistedException
import com.mmpocket.ideapocket.domain.exception.UserNotFoundException
import com.mmpocket.ideapocket.domain.exception.UserPasswordNotMatchedException
import com.mmpocket.ideapocket.domain.service.SequenceGenerator
import com.mmpocket.ideapocket.domain.service.authentication.JwtProvider
import com.mmpocket.ideapocket.domain.user.*
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SignApplication(
    private val repository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtProvider: JwtProvider,
    private val sequenceGenerator: SequenceGenerator
) {
    fun signUp(param: UserParam): User {
        validateExistedUser(param)
        val encodedPassword = passwordEncoder.encode(param.password)
        val userParam = UserParam(nickname = param.nickname, email = param.email, password = encodedPassword)

        val userId = UserId(sequenceGenerator.generate(User::class.java.simpleName))
        val user = User(id = userId, param = userParam)

        return repository.save(user)
    }

    private fun validateExistedUser(param: UserParam) {
        val user = repository.findByEmail(param.email)
        if (user.isPresent) {
            throw UserAlreadyExistedException("이미 존재하는 유저입니다.")
        }
    }

    fun signIn(param: UserLoginParam): String {
        val user = repository.findByEmail(email = param.email).orElseThrow { throw UserNotFoundException("가입되지 않은 이메일입니다.") }

        if (!passwordEncoder.matches(param.password, user.password)) {
            throw UserPasswordNotMatchedException("비밀번호가 일치하지 않습니다.")
        }

        return jwtProvider.generateToken(user)
    }
}