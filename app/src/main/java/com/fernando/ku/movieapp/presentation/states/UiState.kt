package com.fernando.ku.movieapp.presentation.states

import androidx.annotation.StringRes

sealed interface UiState<out T> {
    data object IsLoading : UiState<Nothing>
    data class Success<T>(val data: T?) : UiState<T>
    data class Error(@StringRes val error: Int) : UiState<Nothing>
}