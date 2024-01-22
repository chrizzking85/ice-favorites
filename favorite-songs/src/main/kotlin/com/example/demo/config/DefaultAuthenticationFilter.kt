//package com.example.demo.config
//
//import com.example.demo.service.UserService
//import jakarta.servlet.FilterChain
//import jakarta.servlet.http.HttpServletRequest
//import jakarta.servlet.http.HttpServletResponse
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
//import org.springframework.security.core.context.SecurityContextHolder
//import org.springframework.security.core.userdetails.UserDetailsService
//import org.springframework.stereotype.Component
//import org.springframework.web.filter.OncePerRequestFilter
//
///**
// * Authentication filter that sets up authentication unless it was already
// * set up by `BasicAuthenticationFilter`.
// *
// * It is temporary until the frontend starts sending credentials with all requests.
// */
//@Component
//class DefaultAuthenticationFilter(
//    @Value("\${security.default-user}") private val defaultUser: Boolean,
//    private val userDetailsService: UserService,
//) : OncePerRequestFilter() {
//    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
//        if (SecurityContextHolder.getContext().authentication == null && defaultUser) {
//            val user = userDetailsService.loadUserByUsername(DEFAULT_LOGIN)
//            SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken.authenticated(user, null, user.authorities)
//        }
//        filterChain.doFilter(request, response)
//    }
//
//    companion object {
//        const val DEFAULT_LOGIN = "default"
//    }
//}
