package com.fernando.ku.movieapp.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fernando.ku.movieapp.domain.usecases.GetRemoteMoviesUseCase
import com.fernando.ku.movieapp.domain.usecases.GetUserUseCase
import com.fernando.ku.movieapp.domain.usecases.LoginUserUseCase
import com.fernando.ku.movieapp.presentation.ui.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _route = MutableStateFlow(Routes.LOGIN)
    val route: StateFlow<String> = _route.asStateFlow()


    init {
        verifyIsSessionExist()
    }

    private fun verifyIsSessionExist() = viewModelScope.launch {
        getUserUseCase().subscribe({ userEntity ->
            _route.update {
                if (userEntity != null) {
                    Routes.MOVIES
                } else {
                    Routes.LOGIN
                }
            }
            stopLoading()
        }, ::onError)
    }

    private suspend fun stopLoading() {
        delay(1000L)
        _isLoading.update { false }
    }

    private fun onError(exception: Exception) {
        Timber.e(exception)
    }
}