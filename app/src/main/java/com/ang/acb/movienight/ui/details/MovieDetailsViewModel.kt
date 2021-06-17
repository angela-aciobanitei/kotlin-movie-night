package com.ang.acb.movienight.ui.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ang.acb.movienight.R
import com.ang.acb.movienight.domain.entities.MovieDetails
import com.ang.acb.movienight.domain.usecases.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
) : ViewModel() {

    private val movieId: Long = savedStateHandle.get("movieId")!!

    var movieDetails: MovieDetails? by mutableStateOf(null)
    var isLoading: Boolean by mutableStateOf(false)
    var errorMessage: Int? by mutableStateOf(null)

    init {
        getMovieDetails(movieId)
    }

    private fun getMovieDetails(movieId: Long) {
        viewModelScope.launch {
            isLoading = true
            try {
                movieDetails = getMovieDetailsUseCase(movieId)
            } catch (e: Exception) {
                Timber.e(e)
                errorMessage = R.string.get_movie_details_error_message
            }
            isLoading = false
        }
    }
}