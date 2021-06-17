package com.ang.acb.movienight.ui.favorites

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ang.acb.movienight.R
import com.ang.acb.movienight.domain.entities.Movie
import com.ang.acb.movienight.domain.usecases.GetAllFavoriteMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getAllFavoriteMoviesUseCase: GetAllFavoriteMoviesUseCase,
) : ViewModel() {

    var movies: List<Movie>? by mutableStateOf(null)
    var isLoading: Boolean by mutableStateOf(false)
    var errorMessage: Int? by mutableStateOf(null)

    init {
        getFavoriteMovies()
    }

    private fun getFavoriteMovies() {
        viewModelScope.launch {
            isLoading = true
            getAllFavoriteMoviesUseCase()
                .catch {
                    isLoading = false
                    errorMessage = R.string.get_favorites_error_message
                    Timber.e(it)
                }
                .collect {
                    isLoading = false
                    movies = it
                }
        }
    }
}