package com.ang.acb.movienight.ui.movielist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.ang.acb.movienight.data.MovieRepository
import com.ang.acb.movienight.domain.Movie
import com.ang.acb.movienight.domain.MovieFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
) : ViewModel() {

    var movieFilter: MovieFilter by mutableStateOf(MovieFilter.POPULAR)

    val moviesPaged: Flow<PagingData<Movie>> = movieRepository.getMovies(movieFilter)
}