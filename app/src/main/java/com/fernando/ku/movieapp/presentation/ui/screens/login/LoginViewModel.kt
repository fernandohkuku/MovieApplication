package com.fernando.ku.movieapp.presentation.ui.screens.login

import android.provider.Contacts.Intents.UI
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fernando.ku.movieapp.R
import com.fernando.ku.movieapp.domain.entities.UserEntity
import com.fernando.ku.movieapp.domain.usecases.LoginUserUseCase
import com.fernando.ku.movieapp.presentation.states.UiState
import com.fernando.ku.movieapp.presentation.ui.screens.login.models.LoginForm
import com.fernando.ku.movieapp.presentation.ui.screens.login.models.LoginUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUserUseCase: LoginUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<UserEntity>>(UiState.IsLoading)
    val uiState = _uiState.asStateFlow()

    private val _loginForm = MutableStateFlow(LoginForm())

    val loginForm get() = _loginForm.asStateFlow()

    fun onFieldChanged(event: LoginUiEvent) {
        _loginForm.update {
            when (event) {
                is LoginUiEvent.OnUsernameChange -> it.copy(username = event.value)
                is LoginUiEvent.OnPasswordChange -> it.copy(password = event.value)
            }
        }
    }


    fun onLogin() = viewModelScope.launch {
        val body = LoginUserUseCase.Params(
            username = loginForm.value.username,
            password = loginForm.value.password
        )

        loginUserUseCase(body).fold({ user ->
            _uiState.update { UiState.Success(user) }
        }, ::onError)
    }

    private fun onError(exception: Exception) {
        Timber.e(exception)
        _uiState.update { UiState.Error(R.string.error_login) }
    }


}