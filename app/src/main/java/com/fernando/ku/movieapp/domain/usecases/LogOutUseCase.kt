package com.fernando.ku.movieapp.domain.usecases

import com.fernando.ku.movieapp.domain.repositories.AuthRepository
import com.fernando.ku.movieapp.domain.usecases.base.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class LogOutUseCase @Inject constructor(
    private val repository: AuthRepository,
    background: CoroutineDispatcher
) : UseCase<Unit, Unit>(background) {
    override suspend fun run(input: Unit?) {
        repository.logout()
    }
}