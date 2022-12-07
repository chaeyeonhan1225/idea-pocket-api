package com.mmpocket.ideapocket.domain.service.authentication

import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig(
    private val jwtProvider: JwtProvider
) {
    @Bean
    fun authManager(http: HttpSecurity): AuthenticationManager? {
        val authenticationManagerBuilder = http.getSharedObject(
            AuthenticationManagerBuilder::class.java
        )
        return authenticationManagerBuilder.build()
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf().disable()

        http.authorizeRequests()
            .antMatchers("/v1/sign-up", "/v1/sign-in").permitAll()
            .anyRequest().authenticated()
            .and()
            .logout()
            .permitAll()
            .and()
            .formLogin().disable()
            .headers().frameOptions().disable()
            .and()
            .addFilterBefore(jwtAuthenticationFilter("/v1/sign-in"), UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }

    private fun jwtAuthenticationFilter(processUrl: String) =
        JwtAuthenticationFilter(jwtProvider)

}