package com.ang.acb.movienight.domain

import javax.inject.Inject

class GetFilteredMoviesUseCase @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
) {

    suspend operator fun invoke(filter: MovieFilter, page: Int): Movies {
        return when (filter) {
            MovieFilter.POPULAR -> getPopularMoviesUseCase(page)
            MovieFilter.TOP_RATED -> getTopRatedMoviesUseCase(page)
            MovieFilter.NOW_PLAYING -> getNowPlayingMoviesUseCase(page)
            MovieFilter.UPCOMING -> getUpcomingMoviesUseCase(page)
        }
    }
}