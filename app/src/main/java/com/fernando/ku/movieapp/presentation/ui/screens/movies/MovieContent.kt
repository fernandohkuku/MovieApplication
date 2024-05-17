package com.fernando.ku.movieapp.presentation.ui.screens.movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fernando.ku.movieapp.domain.entities.MovieEntity
import com.fernando.ku.movieapp.ui_ktx.isLastItem

@Composable
fun MovieContent(
    items: List<MovieEntity>,
    listState: () -> LazyListState,
    isScrolling: () -> Boolean,
    onLoadMore: () -> Unit,
    onNavigateToMovieDetailScreen: (Int) -> Unit,

    ) {
    val currentOnLoadMore by rememberUpdatedState(newValue = onLoadMore)

    val shouldStartPaginate by remember {
        derivedStateOf {
            listState().isLastItem()
        }
    }

    LaunchedEffect(key1 = shouldStartPaginate) {
        if (isScrolling()) {
            currentOnLoadMore()
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        state = listState(),
        contentPadding = PaddingValues(
            horizontal = 4.dp,
            vertical = 4.dp
        ),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        items(items, key = { item ->
            item.id
        }){
            MovieCard(movie = it, onMovieClicked = {id->
                onNavigateToMovieDetailScreen(id)
            })
        }
    }

}