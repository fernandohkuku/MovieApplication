package com.fernando.ku.movieapp.presentation.ui.screens.movie_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.fernando.ku.movieapp.domain.entities.MovieEntity
import com.fernando.ku.movieapp.presentation.states.UiState
import com.fernando.ku.movieapp.presentation.ui.navigation.Routes


fun NavController.navigateToMovieDetailScreen(movieId: Int) {
    navigate("${Routes.MOVIE_DETAIL}/${movieId}")
}

@Composable
fun MovieDetailRoute(
    viewModel: MovieDetailViewModel = hiltViewModel(),
    onNavigateToMoviesScreen: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    MovieDetailScreen(
        uiState = uiState,
        onNavigateToMoviesScreen = onNavigateToMoviesScreen
    )
}

@Composable
fun MovieDetailScreen(
    uiState: UiState<MovieEntity>,
    onNavigateToMoviesScreen: () -> Unit
) {
    val scrollState = rememberScrollState()

    when (uiState) {
        is UiState.IsLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is UiState.Success -> {
            uiState.data?.let { movie ->
                Surface(
                    modifier = Modifier
                        .systemBarsPadding()
                        .statusBarsPadding()
                        .verticalScroll(scrollState)
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(model = movie.poster),
                            contentDescription = "Movie Image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = movie.title.toString(),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        Text(
                            text = "Duración: ${movie.vote_average}",
                            style = MaterialTheme.typography.bodyLarge
                        )

                        Text(
                            text = "Fecha de Estreno: ${movie.release_date}",
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Text(
                            text = "Clasificación: ${movie.vote_average}",
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Text(
                            text = "Descripción: ${movie.overview}",
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = movie.overview.toString(),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

        }

        is UiState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Some error")
            }
        }
    }

}