package com.fernando.ku.movieapp.presentation.ui.screens.login.models

sealed interface LoginUiEvent {
    data class OnUsernameChange(val value: String) : LoginUiEvent
    data class OnPasswordChange(val value: String) : LoginUiEvent
}