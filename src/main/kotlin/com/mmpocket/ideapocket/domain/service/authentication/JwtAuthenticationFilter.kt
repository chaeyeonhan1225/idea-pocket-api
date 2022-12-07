package com.mmpocket.ideapocket.domain.service.authentication

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

class JwtAuthenticationFilter(
    private val jwtProvider: JwtProvider
): GenericFilterBean() {
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val token = request?.let { extractToken(request as HttpServletRequest) }

        if (token != null && jwtProvider.validateToken(token)) {
            val authentication = jwtProvider.getAuthentication(token)
            SecurityContextHolder.getContext().authentication = authentication
        }

        chain?.doFilter(request, response)
    }

    // TODO: Bearer 토큰인 경우
    private fun extractToken(request: HttpServletRequest): String? =  request.getHeader("Authorization")
}