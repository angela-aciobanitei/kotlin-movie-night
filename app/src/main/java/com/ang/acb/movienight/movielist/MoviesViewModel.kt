package com.ang.acb.movienight.movielist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.ang.acb.movienight.data.MovieRepository
import com.ang.acb.movienight.domain.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
) : ViewModel() {

    val moviesPaged: Flow<PagingData<Movie>> = movieRepository.getPopularMoviesStream()
    var movies: List<Movie> by mutableStateOf(emptyList())

    fun getStuff() {
        viewModelScope.launch {
            movies = movieRepository.getPopularMovies()
        }
    }
}