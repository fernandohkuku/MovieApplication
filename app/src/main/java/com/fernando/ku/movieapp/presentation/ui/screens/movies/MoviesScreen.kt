package com.fernando.ku.movieapp.presentation.ui.screens.movies

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.fernando.ku.movieapp.R
import com.fernando.ku.movieapp.domain.entities.MovieEntity
import com.fernando.ku.movieapp.presentation.components.DefaultAppBar
import com.fernando.ku.movieapp.presentation.states.UiState
import com.fernando.ku.movieapp.presentation.ui.navigation.Routes

fun NavController.navigateToMoviesScreen() {
    navigate(Routes.MOVIES)
}

@Composable
fun MoviesRoute(
    viewModel: MoviesViewModel = hiltViewModel(),
    onNavigateToMovieDetailScreen: (Int) -> Unit,
    onNavigateToLoginScreen: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val isLogout by viewModel.isLogout.collectAsStateWithLifecycle()

    MoviesScreen(
        uiState = uiState,
        isLogout = isLogout,
        onNavigateToMovieDetailScreen = onNavigateToMovieDetailScreen,
        onNavigateToLoginScreen = onNavigateToLoginScreen,
        onGetMoreMovies = viewModel::getMoreMovies,
        onLogout = viewModel::onLogout
    )
}

@Composable
fun MoviesScreen(
    uiState: UiState<List<MovieEntity>>,
    isLogout: Boolean,
    onNavigateToLoginScreen: () -> Unit,
    onGetMoreMovies: () -> Unit,
    onNavigateToMovieDetailScreen: (Int) -> Unit,
    onLogout: () -> Unit,
) {
    val currentOnNavigateToLoginScreen by rememberUpdatedState(newValue = onNavigateToLoginScreen)

    val listState = rememberLazyListState()


    val isScrolling by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0
        }
    }


    LaunchedEffect(key1 = isLogout) {
        if (isLogout) {
            currentOnNavigateToLoginScreen()
        }
    }

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        DefaultAppBar(
            title = stringResource(id = R.string.movies_title),
            actions = {
                IconButton(onClick = {
                    onLogout()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_exit_to_app_24),
                        contentDescription = null
                    )
                }
            }
        )

        when (uiState) {
            is UiState.IsLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            is UiState.Success -> {
                uiState.data?.let {
                    MovieContent(
                        items = uiState.data,
                        listState = { listState },
                        isScrolling = { isScrolling },
                        onLoadMore = onGetMoreMovies,
                        onNavigateToMovieDetailScreen = onNavigateToMovieDetailScreen
                    )
                }
            }

            is UiState.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Some error")
                }
            }

        }
    }

}