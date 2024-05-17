package com.fernando.ku.movieapp.presentation.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.fernando.ku.movieapp.presentation.ui.theme.MovieAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultAppBar(
    title: String,
    actions: @Composable RowScope.() -> Unit = {}
) {
    TopAppBar(
        title = { Text(text = title) },
        actions = { actions() }
    )
}

@Composable
@Preview
fun PreviewDefaultAppBar(){
    MovieAppTheme {
        DefaultAppBar(title = "Movies")
    }
}