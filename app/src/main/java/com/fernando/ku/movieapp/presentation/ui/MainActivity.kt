package com.fernando.ku.movieapp.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.fernando.ku.movieapp.presentation.ui.navigation.RootNavigationGraph
import com.fernando.ku.movieapp.presentation.ui.theme.MovieAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            viewModel.isLoading.value
        }
        enableEdgeToEdge()
        setContent {
            MovieAppTheme {
                val route by viewModel.route.collectAsStateWithLifecycle()

                val navController = rememberNavController()

                RootNavigationGraph(navController = navController, route)

                 /*Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                     LazyColumn(modifier = Modifier.padding(innerPadding)) {
                         items(movies.itemCount) {
                             Text(text = movies[it]!!.title)
                         }
                     }
                 }*/
            }
        }
    }
}