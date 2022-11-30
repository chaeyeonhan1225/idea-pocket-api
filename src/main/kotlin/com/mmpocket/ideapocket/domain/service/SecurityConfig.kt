package com.mmpocket.ideapocket.domain.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.BeanIds
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig(
    private val userDetailsService: UserDetailsService
) {

//    @Bean
//    fun authenticationManager(
//        httpSecurity: HttpSecurity,
//        userDetailsService: UserDetailsService,
//    ): AuthenticationManager? {
//        val authenticationManagerBuilder = httpSecurity.getSharedObject(
//            AuthenticationManagerBuilder::class.java
//        )
//        authenticationManagerBuilder.userDetailsService(userDetailsService)
//
//        return authenticationManagerBuilder.build()
//    }

//    @Bean(name = [BeanIds.AUTHENTICATION_MANAGER])
//    fun authenticationManagerBean(): AuthenticationManager {
//        return super.authenticationManagerBean()
//    }


    fun configureAuthentication(authenticationManagerBuilder: AuthenticationManagerBuilder) {
        authenticationManagerBuilder
             .userDetailsService(userDetailsService)
             .passwordEncoder(passwordEncoder())
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf().disable()

//        http.authorizeRequests()
//            .antMatchers("/sign-up", "/sign-in").permitAll()
//            .antMatchers("/**").authenticated()

        return http.build()
    }
}