package com.fernando.ku.movieapp.presentation.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.fernando.ku.movieapp.domain.entities.UserEntity
import com.fernando.ku.movieapp.presentation.states.UiState
import com.fernando.ku.movieapp.presentation.ui.navigation.Routes
import com.fernando.ku.movieapp.presentation.ui.screens.login.models.LoginForm
import com.fernando.ku.movieapp.presentation.ui.screens.login.models.LoginUiEvent

fun NavController.navigateToLoginScreen() {
    navigate(Routes.LOGIN)
}


@Composable
fun LoginRoute(
    viewModel: LoginViewModel = hiltViewModel(),
    onNavigateToMoviesScreen: () -> Unit
) {

    val loginForm by viewModel.loginForm.collectAsStateWithLifecycle()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LoginScreen(
        uiState = uiState,
        loginForm = loginForm,
        onUsernameChanged = viewModel::onFieldChanged,
        onPasswordChanged = viewModel::onFieldChanged,
        onNavigateToMoviesScreen = onNavigateToMoviesScreen,
        onLogin = viewModel::onLogin
    )
}

@Composable
fun LoginScreen(
    uiState: UiState<UserEntity>,
    loginForm: LoginForm,
    onUsernameChanged: (LoginUiEvent) -> Unit,
    onPasswordChanged: (LoginUiEvent) -> Unit,
    onNavigateToMoviesScreen: () -> Unit,
    onLogin: () -> Unit
) {

    val currentOnNavigateToMoviesScreen by rememberUpdatedState(newValue = onNavigateToMoviesScreen)

    LaunchedEffect(key1 = uiState) {
        if (uiState is UiState.Success) {
            currentOnNavigateToMoviesScreen()
        }
    }

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoginContent(
            loginForm = loginForm,
            onUsernameChanged = onUsernameChanged,
            onPasswordChanged = onPasswordChanged,
            onLogin = onLogin
        )

        if (uiState is UiState.Error) {
            Text(text = stringResource(id = uiState.error), color = MaterialTheme.colorScheme.error)
        }
    }


}