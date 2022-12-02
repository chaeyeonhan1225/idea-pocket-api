package com.mmpocket.ideapocket.domain.service

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


//@Component
//class CustomUsernamePasswordAuthenticationFilter(
//    private val authenticationManager: AuthenticationManager
//): AbstractAuthenticationProcessingFilter(
//    DEFAULT_ANT_PATH_REQUEST_MATCHER,
//    authenticationManager
//
//) {
//    private val DEFAULT_ANT_PATH_REQUEST_MATCHER = AntPathRequestMatcher(
//        "/login",
//        "POST"
//    )
//
//    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
//        TODO("Not yet implemented")
//        val username = "test"
//        val password = "12345"
//
//
//        val authRequest = UsernamePasswordAuthenticationToken.unauthenticated(
//            username,
//            password
//        )
//
//        return authenticationManager.authenticate(authRequest)
//    }
//}