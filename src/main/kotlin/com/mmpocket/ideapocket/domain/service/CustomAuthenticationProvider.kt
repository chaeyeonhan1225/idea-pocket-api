package com.mmpocket.ideapocket.domain.service

import com.mmpocket.ideapocket.domain.exception.UserDisabledException
import com.mmpocket.ideapocket.domain.exception.UserPasswordNotMatchedException
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationProvider(
    private val userDetailsService: UserDetailsService,
    private val passwordEncoder: PasswordEncoder
) : AuthenticationProvider {
    override fun authenticate(authentication: Authentication?): Authentication {
        return authentication?.let {
            println("authentication = " + it)
            val username = it.name as String
            val password = it.credentials as String

            val user = userDetailsService.loadUserByUsername(username)

            if (!passwordEncoder.matches(password, user.password)) {
                throw UserPasswordNotMatchedException("비밀번호가 일치하지 않습니다.")
            }

            if (!user.isEnabled) {
                throw UserDisabledException("비활성화된 유저입니다.")
            }

            UsernamePasswordAuthenticationToken(username, password, user.authorities)
        } ?: throw NotFoundException()
    }

    // TODO: 구현 필요
    override fun supports(authentication: Class<*>?): Boolean {
        return true
    }
}