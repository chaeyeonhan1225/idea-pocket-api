package com.mmpocket.ideapocket.controller

import com.mmpocket.ideapocket.application.SignApplication
import com.mmpocket.ideapocket.application.UserApplication
import com.mmpocket.ideapocket.application.UserProvider
import com.mmpocket.ideapocket.domain.exception.UserPasswordNotMatchedException
import com.mmpocket.ideapocket.domain.service.authentication.JwtProvider
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
    private val application: SignApplication
) {
    @PostMapping("/sign-up")
    fun signUp(@RequestBody param: UserParam): User = application.signUp(param)

    @PostMapping("/sign-in")
    fun signIn(@RequestBody param: UserLoginParam): String {
        return application.signIn(param)
    }
}