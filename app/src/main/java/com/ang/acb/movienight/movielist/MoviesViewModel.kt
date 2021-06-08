package com.ang.acb.movienight.movielist

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
    private val movieRepository: MovieRepository
) : ViewModel() {

    val movies: Flow<PagingData<Movie>> = movieRepository.getPopularMoviesStream()
}