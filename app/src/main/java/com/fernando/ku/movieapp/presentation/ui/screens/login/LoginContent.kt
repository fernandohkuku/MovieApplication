package com.fernando.ku.movieapp.presentation.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fernando.ku.movieapp.presentation.ui.screens.login.models.LoginForm
import com.fernando.ku.movieapp.presentation.ui.screens.login.models.LoginUiEvent
import com.fernando.ku.movieapp.presentation.ui.theme.MovieAppTheme

@Composable
fun LoginContent(
    loginForm: LoginForm,
    onUsernameChanged: (LoginUiEvent) -> Unit,
    onPasswordChanged: (LoginUiEvent) -> Unit,
    onLogin: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Login", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = loginForm.username,
            onValueChange = {
                onUsernameChanged(LoginUiEvent.OnUsernameChange(it))
            },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = loginForm.password,
            onValueChange = {
                onPasswordChanged(LoginUiEvent.OnPasswordChange(it))
            },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onLogin,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }
    }
}

@Composable
@Preview
fun PreviewLoginContent() {
    MovieAppTheme {
        LoginContent(
            loginForm = LoginForm(
                username = "",
                password = ""
            ),
            onUsernameChanged = {},
            onPasswordChanged = {},
            onLogin = {}
        )
    }
}