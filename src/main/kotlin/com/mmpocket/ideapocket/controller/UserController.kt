package com.mmpocket.ideapocket.controller

import com.mmpocket.ideapocket.application.UserApplication
import com.mmpocket.ideapocket.domain.user.User
import com.mmpocket.ideapocket.domain.user.UserParam
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("users")
class UserController(
    private val application: UserApplication,
) {

}