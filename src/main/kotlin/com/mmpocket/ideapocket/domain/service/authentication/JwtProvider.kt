package com.mmpocket.ideapocket.domain.service.authentication

import com.mmpocket.ideapocket.domain.service.UserDetailsService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.Base64

@Component
class JwtProvider(  // TODO: 이름 변경 필요
    private val userDetailsService: UserDetailsService,
) {

    private val secretKey = "secret"
    private val encodedSecretKey = Base64.getEncoder().encode(secretKey.toByteArray())

    fun generateToken(user: UserDetails): String {
        val claims = mapOf(
            "username" to user.username,
            "authorities" to user.authorities
        )

        // TODO: expiration 설정 해줘야함
        return Jwts.builder()
            .setClaims(claims)
            .signWith(SignatureAlgorithm.HS256, encodedSecretKey)
            .compact()
    }

    fun validateToken(token: String): Boolean {
        val claims = getClaims(token)
        // TODO: expiration 계산도 해야함
        val isValid =
                claims.containsKey("username") &&
                claims.containsKey("authorities")

        return isValid
    }

    fun getAuthentication(token: String): Authentication {
        val claim = getClaims(token)
        val username = claim.get("username").toString()
        val user = userDetailsService.loadUserByUsername(username)
        return UsernamePasswordAuthenticationToken(user, "", mutableListOf())
    }

    fun getClaims(token: String): Claims =
        Jwts.parser().setSigningKey(encodedSecretKey).parseClaimsJws(token).body

}