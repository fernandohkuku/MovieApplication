package com.fernando.ku.movieapp.data.local.source

import com.fernando.ku.movieapp.data.local.dao.UserDao
import com.fernando.ku.movieapp.data.local.models.UserModel
import com.fernando.ku.movieapp.data.mappers.toModel
import com.fernando.ku.movieapp.data.repositories.AuthenticationLocalDataSource
import com.fernando.ku.movieapp.domain.entities.UserEntity
import javax.inject.Inject

class AuthenticationLocalDataSourceImpl @Inject constructor(
    private val userDao: UserDao
) : AuthenticationLocalDataSource {

    override suspend fun getUser(): UserModel? {
        return userDao.getUser()
    }


    override suspend fun setUser(userEntity: UserEntity) {
        val userModel = userEntity.toModel()
        userDao.insertUser(userModel)
    }

    override suspend fun logout() {
        userDao.deleteUser()
    }
}