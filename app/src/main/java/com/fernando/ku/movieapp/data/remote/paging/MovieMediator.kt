package com.fernando.ku.movieapp.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fernando.ku.movieapp.data.local.AppDatabase
import com.fernando.ku.movieapp.data.mappers.toModel
import com.fernando.ku.movieapp.data.models.base.MovieDto
import com.fernando.ku.movieapp.data.remote.api.MovieService

class MovieMediator(
    private val service: MovieService,
    private val db: AppDatabase
) : PagingSource<Int, MovieDto>() {
    override fun getRefreshKey(state: PagingState<Int, MovieDto>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDto> =
        try {
            val nextPage = params.currentPage() ?: FIRST_PAGE

            val envelope = service.getDiscoverMovies(1)
            db.movieDao().insertAll(envelope.results.map { it.toModel() })

            LoadResult.Page(
                envelope.results,
                prevKey = if (nextPage == FIRST_PAGE) null else nextPage - 1,
                nextKey = if (envelope.results.isEmpty()) null else nextPage + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }




    private fun LoadParams<Int>.currentPage(): Int? = when (this) {
        is LoadParams.Refresh -> FIRST_PAGE
        else -> key
    }

    companion object {
        const val FIRST_PAGE = 1
    }
}