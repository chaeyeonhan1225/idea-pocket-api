package com.mmpocket.ideapocket.domain.service

 import com.mmpocket.ideapocket.domain.exception.UserNotFoundException
import com.mmpocket.ideapocket.domain.user.UserRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsService(
    private val userRepository: UserRepository
): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        return username?.let {
            println("username = " + username)
            userRepository.findByEmail(username).orElseThrow { UserNotFoundException("존재하지 않는 이메일입니다.") }
        } ?: throw NotFoundException()  // TODO: Custom Exception 구현
    }
}