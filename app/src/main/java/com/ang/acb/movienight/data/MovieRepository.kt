package com.ang.acb.movienight.data

import com.ang.acb.movienight.data.local.FavoriteMovie
import com.ang.acb.movienight.data.local.LocalMovieDataSource
import com.ang.acb.movienight.data.remote.RemoteMovieDataSource
import com.ang.acb.movienight.domain.Movie
import com.ang.acb.movienight.domain.MovieGateway
import com.ang.acb.movienight.domain.Movies
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val remoteMovieDataSource: RemoteMovieDataSource,
    private val localMovieDataSource: LocalMovieDataSource,
) : MovieGateway {

    override suspend fun getPopularMovies(page: Int): Movies {
        return remoteMovieDataSource.getPopularMovies(page)
    }

    override suspend fun getTopRatedMovies(page: Int): Movies {
        return remoteMovieDataSource.getTopRatedMovies(page)
    }

    override suspend fun getNowPlayingMovies(page: Int): Movies {
        return remoteMovieDataSource.getNowPlayingMovies(page)
    }

    override suspend fun getUpcomingMovies(page: Int): Movies {
        return remoteMovieDataSource.getUpcomingMovies(page)
    }

    override suspend fun searchMovies(query: String, page: Int): Movies {
        return remoteMovieDataSource.searchMovies(query, page)
    }

    override suspend fun saveFavoriteMovie(movie: Movie): Long {
        return localMovieDataSource.saveFavoriteMovie(
            FavoriteMovie(
                id = movie.id,
                title = movie.title,
                overview = movie.overview,
                releaseDate = movie.releaseDate,
                posterPath = movie.posterPath,
                backdropPath = movie.backdropPath,
                popularity = movie.popularity,
                voteAverage = movie.voteAverage,
                voteCount = movie.voteCount,
                isFavorite = true
            )
        )
    }

    override fun getFavoriteMovie(movieId: Long): Flow<Movie> {
        return localMovieDataSource.getFavoriteMovie(movieId).map { it.asMovie() }
    }

    override fun getAllFavoriteMovies(): Flow<List<Movie>> {
        return localMovieDataSource.getAllFavoriteMovies().map { it.asMovies() }
    }

    override suspend fun updateFavoriteFlag(movieId: String, isFavorite: Boolean) {
        return localMovieDataSource.updateFavoriteFlag(movieId, isFavorite)
    }

    override suspend fun deleteFavoriteMovie(movieId: String): Int {
        return localMovieDataSource.deleteFavoriteMovie(movieId)
    }

    override suspend fun deleteAllFavoriteMovies() {
        return localMovieDataSource.deleteAllFavoriteMovies()
    }
}