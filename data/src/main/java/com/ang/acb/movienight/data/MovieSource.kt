package com.ang.acb.movienight.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ang.acb.movienight.domain.Movie
import timber.log.Timber

private const val STARTING_PAGE_INDEX = 1

class MovieSource(
    private val movieService: MovieService
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = movieService.getPopularMovies(page)

            Timber.d("asd ${response.results.asMovies()}")

            LoadResult.Page(
                data = response.results.asMovies(),
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (page == response.totalPages) null else page + 1
            )
        } catch (e: Exception) {
            Timber.e("asd $e")
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        TODO("Not yet implemented")
    }
}