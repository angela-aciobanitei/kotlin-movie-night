package com.ang.acb.movienight.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ang.acb.movienight.domain.Movie
import com.ang.acb.movienight.domain.MovieGateway
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieService: MovieService
) : MovieGateway {

    fun getPopularMoviesStream(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { MovieSource(movieService) }
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 25
    }

    override suspend fun getPopularMovies(): List<Movie> {
        return try {
            val response = movieService.getPopularMoviesSuspend()
            val result = response.results.asMovies()
            Timber.e("asd $result")
            result
        } catch (e: Exception) {
            Timber.e("asd $e")
            emptyList()
        }
    }
}