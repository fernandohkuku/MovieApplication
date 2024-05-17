package com.fernando.ku.movieapp.ui_ktx

import androidx.compose.foundation.lazy.LazyListState

fun LazyListState.isLastItem(): Boolean {
    val lastVisibleItemIndex = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: -1
    val totalItemCount = layoutInfo.totalItemsCount
    val threshold = 6
    return lastVisibleItemIndex != -1 && totalItemCount > 0 && lastVisibleItemIndex == totalItemCount - 1
}