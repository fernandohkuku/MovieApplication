package com.fernando.ku.movieapp.presentation.ui.screens.movie_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fernando.ku.movieapp.domain.entities.MovieEntity
import com.fernando.ku.movieapp.domain.usecases.GetMovieByIdUseCase
import com.fernando.ku.movieapp.presentation.states.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMovieByIdUseCase: GetMovieByIdUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<MovieEntity>>(UiState.IsLoading)
    val uiState get() = _uiState.asStateFlow()

    private val movieId = checkNotNull(savedStateHandle.get<Int>("movieId"))

    init {
        getMovieById()
    }

    private fun getMovieById() = viewModelScope.launch {
        getMovieByIdUseCase(movieId).fold({ movie ->
            _uiState.update { UiState.Success(movie) }
        }, ::onError)
    }

    private fun onError(exception: Exception) {
        Timber.e(exception)
    }

}