package com.fernando.ku.movieapp.presentation.ui.screens.login.models

import com.fernando.ku.movieapp.ui_ktx.emptyString

data class LoginForm(
    val username: String = emptyString(),
    val password: String = emptyString()
)