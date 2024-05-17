package com.fernando.ku.movieapp.data.repositories

import com.fernando.ku.movieapp.data.local.models.UserModel
import com.fernando.ku.movieapp.data.local.source.AuthenticationLocalDataSourceImpl
import com.fernando.ku.movieapp.data.mappers.toEntity
import com.fernando.ku.movieapp.domain.entities.UserEntity
import com.fernando.ku.movieapp.domain.repositories.AuthRepository
import com.google.firebase.auth.AuthResult
import javax.inject.Inject

internal class AuthenticationRepositoryImpl @Inject constructor(
    private val remoteDataSource: AuthenticationRemoteDataSource,
    private val localDataSource: AuthenticationLocalDataSource
) : AuthRepository {
    override suspend fun getUser(): UserEntity? {
        return localDataSource.getUser()?.toEntity()
    }

    override suspend fun login(username: String, password: String): UserEntity? {
        return remoteDataSource.login(username, password)?.let {
            localDataSource.setUser(it)
            it
        }
    }

    override suspend fun logout() {
        remoteDataSource.logout()
        localDataSource.logout()
    }

}

internal interface AuthenticationRemoteDataSource {
    suspend fun login(username: String, password: String): UserEntity
    suspend fun logout()
}

internal interface AuthenticationLocalDataSource {
    suspend fun getUser(): UserModel?
    suspend fun setUser(userEntity: UserEntity)
    suspend fun logout()
}