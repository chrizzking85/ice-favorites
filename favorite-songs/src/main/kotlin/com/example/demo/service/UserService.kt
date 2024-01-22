package com.example.demo.service

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class UserService(
) {
    fun getCurrentUserName(): String {
        val auth =
            SecurityContextHolder.getContext().authentication ?: throw IllegalArgumentException("No current user")

        return auth.name
    }
}