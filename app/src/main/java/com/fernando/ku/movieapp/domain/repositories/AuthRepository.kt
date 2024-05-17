package com.fernando.ku.movieapp.domain.repositories

import com.fernando.ku.movieapp.domain.entities.UserEntity

interface AuthRepository {
    suspend fun getUser(): UserEntity?
    suspend fun login(username: String, password: String): UserEntity?
    suspend fun logout()
}