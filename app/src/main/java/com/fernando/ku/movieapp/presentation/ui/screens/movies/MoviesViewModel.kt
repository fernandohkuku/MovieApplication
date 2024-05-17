package com.fernando.ku.movieapp.presentation.ui.screens.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fernando.ku.movieapp.domain.entities.MovieEntity
import com.fernando.ku.movieapp.domain.repositories.MovieRepository
import com.fernando.ku.movieapp.domain.usecases.GetMoviesUseCase
import com.fernando.ku.movieapp.domain.usecases.GetRemoteMoviesUseCase
import com.fernando.ku.movieapp.domain.usecases.LogOutUseCase
import com.fernando.ku.movieapp.presentation.states.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getRemoteMoviesUseCase: GetRemoteMoviesUseCase,
    private val getMoviesUseCase: GetMoviesUseCase,
    private val logOutUseCase: LogOutUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<MovieEntity>>>(UiState.IsLoading)
    val uiState = _uiState.asStateFlow()

    private val _isLogout = MutableStateFlow(false)
    val isLogout = _isLogout.asStateFlow()

    //val movies get() = getRemoteMoviesUseCase(onFailure = ::onError).cachedIn(viewModelScope)

    init {
        getMovies()
    }


    private fun getMovies() = viewModelScope.launch {
        val params = MovieRepository.PageDetails(
            isFirstPage = true,
            size = 10
        )
        getMoviesUseCase(params).fold({ movies ->
            _uiState.update { UiState.Success(movies) }
        }, ::onError)
    }

    fun getMoreMovies() = viewModelScope.launch {
        val params = MovieRepository.PageDetails(
            isFirstPage = false,
            size = 20
        )

        getMoviesUseCase(params).fold({ movies ->
            _uiState.update { UiState.Success(movies) }
        }, ::onError)
    }

    fun onLogout() = viewModelScope.launch {
        logOutUseCase().fold({
            _isLogout.update { true }
        }, ::onError)
    }

    private fun onError(exception: Exception) {
        Timber.e(exception)
    }
}