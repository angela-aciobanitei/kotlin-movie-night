package com.ang.acb.movienight.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ang.acb.movienight.domain.GetFilteredMoviesUseCase
import com.ang.acb.movienight.domain.Movie
import com.ang.acb.movienight.domain.MovieFilter
import timber.log.Timber

private const val STARTING_PAGE_INDEX = 1

class MoviesPagingSource(
    private val filter: MovieFilter,
    private val getFilteredMoviesUseCase: GetFilteredMoviesUseCase,
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = getFilteredMoviesUseCase(filter, page)

            LoadResult.Page(
                data = response.movies,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (page == response.totalPages) null else page + 1
            )

        } catch (e: Exception) {
            Timber.e(e)
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }
}