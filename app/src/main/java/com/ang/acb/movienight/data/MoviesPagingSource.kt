package com.ang.acb.movienight.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ang.acb.movienight.domain.Movie
import com.ang.acb.movienight.domain.MovieFilter
import timber.log.Timber

private const val STARTING_PAGE_INDEX = 1

class MoviesPagingSource(
    private val movieService: MovieService,
    private val filter: MovieFilter,
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = when (filter) {
                MovieFilter.POPULAR -> movieService.getPopularMovies(page)
                MovieFilter.TOP_RATED -> movieService.getTopRatedMovies(page)
                MovieFilter.NOW_PLAYING -> movieService.getNowPlayingMovies(page)
                MovieFilter.UPCOMING -> movieService.getUpcomingMovies(page)
            }

            LoadResult.Page(
                data = response.results.asMovies(),
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