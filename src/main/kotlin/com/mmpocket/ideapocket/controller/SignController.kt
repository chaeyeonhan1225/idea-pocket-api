package com.mmpocket.ideapocket.controller

import com.mmpocket.ideapocket.application.UserApplication
import com.mmpocket.ideapocket.application.UserProvider
import com.mmpocket.ideapocket.domain.user.User
import com.mmpocket.ideapocket.domain.user.UserLoginParam
import com.mmpocket.ideapocket.domain.user.UserParam
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1")
class SignController(
    private val application: UserApplication,
    private val provider: UserProvider,
    private val passwordEncoder: PasswordEncoder
) {
    @PostMapping("/sign-up")
    fun signUp(@RequestBody param: UserParam): User {
        val encodedPassword = passwordEncoder.encode(param.password)
        val userParam = UserParam(nickname = param.nickname, email = param.email, password = encodedPassword)
        return application.createUser(userParam)
    }

//    @PostMapping("/sign-in")
//    fun signIn(@RequestBody param: UserLoginParam): Boolean {
//        val user = provider.findByEmail(email = param.email)
//
//        if (!passwordEncoder.matches(param.password, user.password)) {
//            throw RuntimeException()
//        }
//        return true
//    }
}