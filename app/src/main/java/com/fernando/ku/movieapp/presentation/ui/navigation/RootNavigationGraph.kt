package com.fernando.ku.movieapp.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fernando.ku.movieapp.presentation.ui.screens.login.LoginRoute
import com.fernando.ku.movieapp.presentation.ui.screens.login.navigateToLoginScreen
import com.fernando.ku.movieapp.presentation.ui.screens.movie_detail.MovieDetailRoute
import com.fernando.ku.movieapp.presentation.ui.screens.movie_detail.navigateToMovieDetailScreen
import com.fernando.ku.movieapp.presentation.ui.screens.movies.MoviesRoute
import com.fernando.ku.movieapp.presentation.ui.screens.movies.navigateToMoviesScreen
import com.fernando.ku.movieapp.ui_ktx.addArguments

@Composable
fun RootNavigationGraph(
    navController: NavHostController,
    startDestination: String,
) {
    NavHost(
        navController = navController,
        route = "root_graph",
        startDestination = startDestination
    ) {
        composable(
            route = Routes.LOGIN
        ) {
            LoginRoute(onNavigateToMoviesScreen = navController::navigateToMoviesScreen)
        }

        composable(
            route = Routes.MOVIES
        ) {
            MoviesRoute(
                onNavigateToMovieDetailScreen = navController::navigateToMovieDetailScreen,
                onNavigateToLoginScreen = navController::navigateToLoginScreen
            )
        }

        composable(
            route = "${Routes.MOVIE_DETAIL}/{movieId}",
            arguments = addArguments(
                "movieId" to NavType.IntType
            )
        ) {
            MovieDetailRoute(onNavigateToMoviesScreen = navController::navigateToMoviesScreen)
        }
    }
}

object Routes {
    const val LOGIN = "login_screen"
    const val MOVIES = "movies_screen"
    const val MOVIE_DETAIL = "movie_detail_screen"
}