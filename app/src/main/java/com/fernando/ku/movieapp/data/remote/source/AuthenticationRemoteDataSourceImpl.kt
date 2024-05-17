package com.fernando.ku.movieapp.data.remote.source

import com.fernando.ku.movieapp.data.repositories.AuthenticationRemoteDataSource
import com.fernando.ku.movieapp.domain.entities.UserEntity
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

internal class AuthenticationRemoteDataSourceImpl @Inject constructor(

) : AuthenticationRemoteDataSource {

    override suspend fun login(username: String, password: String): UserEntity {
        val user = Firebase.auth.signInWithEmailAndPassword(username, password).await()
        return UserEntity(user.user?.uid, username)
    }

    override suspend fun logout() {
        Firebase.auth.signOut()
    }
}