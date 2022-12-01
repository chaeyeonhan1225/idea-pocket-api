package com.mmpocket.ideapocket.domain.service

import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
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
    private val authenticationProvider: CustomAuthenticationProvider
) {
    @Bean
    fun authManager(http: HttpSecurity): AuthenticationManager? {
        val authenticationManagerBuilder = http.getSharedObject(
            AuthenticationManagerBuilder::class.java
        )
        authenticationManagerBuilder.authenticationProvider(authenticationProvider)
        return authenticationManagerBuilder.build()
    }


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


//    fun configureAuthentication(authenticationManagerBuilder: AuthenticationManagerBuilder) {
//        authenticationManagerBuilder
//             .userDetailsService(userDetailsService)
//             .passwordEncoder(passwordEncoder())
//    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf().disable()

        http.authorizeRequests()
            .antMatchers("/v1/sign-up", "/v1/sign-in").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginProcessingUrl("/v1/sign-in")
            .permitAll()
            .and()
            .logout()
            .permitAll()

        return http.build()
    }
}