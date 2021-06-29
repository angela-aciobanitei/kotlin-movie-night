package com.ang.acb.movienight.ui.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ang.acb.movienight.R
import com.ang.acb.movienight.domain.entities.Movie
import com.ang.acb.movienight.domain.entities.MovieDetails
import com.ang.acb.movienight.domain.usecases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getSimilarMoviesUseCase: GetSimilarMoviesUseCase,
    private val saveFavoriteMovieUseCase: SaveFavoriteMovieUseCase,
    private val deleteFavoriteMovieUseCase: DeleteFavoriteMovieUseCase,
    private val getFavoriteMovieUseCase: GetFavoriteMovieUseCase,
) : ViewModel() {

    val movieId: Long = savedStateHandle.get("movieId")!!

    var movieDetails: MovieDetails? by mutableStateOf(null)
    var similarMovies: List<Movie> by mutableStateOf(emptyList())
    var isFavorite: Boolean? by mutableStateOf(null)
    var isFavoriteLoading: Boolean by mutableStateOf(false)
    var isLoading: Boolean by mutableStateOf(false)
    var errorMessage: Int? by mutableStateOf(null)

    init {
        getMovieDetails(movieId)
        getSimilarMovies(movieId)
        getIsFavorite(movieId)
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

    private fun getSimilarMovies(movieId: Long) {
        viewModelScope.launch {
            isLoading = true
            try {
                similarMovies = getSimilarMoviesUseCase(movieId)
            } catch (e: Exception) {
                Timber.e(e)
            }
            isLoading = false
        }
    }

    private fun getIsFavorite(movieId: Long) {
        viewModelScope.launch {
            isFavoriteLoading = true
            getFavoriteMovieUseCase(movieId)
                .catch {
                    Timber.e(it)
                    isFavoriteLoading = false
                }
                .collect {
                    isFavorite = it?.isFavorite
                    isFavoriteLoading = false
                }
        }
    }

    fun onFavoriteClicked() {
        viewModelScope.launch {
            isFavoriteLoading = true
            if (isFavorite == true) {
                val deleted = deleteFavoriteMovieUseCase(movieId)
                isFavorite = deleted.equals(1)
                Timber.d("asd deleted from favorites $deleted movie with id = $movieId")
            } else {
                val savedId = movieDetails?.movie?.let { saveFavoriteMovieUseCase(it) }
                isFavorite = savedId?.equals(movieId)
                Timber.d("asd saved to favorites $savedId movie with id = $movieId")
            }
            isFavoriteLoading = false
        }
    }
}