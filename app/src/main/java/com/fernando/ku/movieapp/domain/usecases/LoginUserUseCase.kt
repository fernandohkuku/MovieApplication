package com.fernando.ku.movieapp.domain.usecases

import com.fernando.ku.movieapp.domain.entities.UserEntity
import com.fernando.ku.movieapp.domain.repositories.AuthRepository
import com.fernando.ku.movieapp.domain.usecases.base.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(
    private val repository: AuthRepository,
    background: CoroutineDispatcher
) : UseCase<UserEntity?, LoginUserUseCase.Params>(background) {


    data class Params(
        val username: String,
        val password: String
    )

    override suspend fun run(input: Params?): UserEntity? {
        requireNotNull(input) { "username and password can't be null" }
        return repository.login(input.username, input.password)
    }
}