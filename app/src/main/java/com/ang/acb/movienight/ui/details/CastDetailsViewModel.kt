package com.ang.acb.movienight.ui.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ang.acb.movienight.R
import com.ang.acb.movienight.domain.entities.CastDetails
import com.ang.acb.movienight.domain.usecases.GetCastDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CastDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCastDetailsUseCase: GetCastDetailsUseCase,
) : ViewModel() {

    private val movieId: Long = savedStateHandle.get("castId")!!

    var castDetails: CastDetails? by mutableStateOf(null)
    var isLoading: Boolean by mutableStateOf(false)
    var errorMessage: Int? by mutableStateOf(null)

    init {
        getMovieDetails(movieId)
    }

    private fun getMovieDetails(movieId: Long) {
        viewModelScope.launch {
            isLoading = true
            try {
                castDetails = getCastDetailsUseCase(movieId)
            } catch (e: Exception) {
                Timber.e(e)
                errorMessage = R.string.get_movie_details_error_message
            }
            isLoading = false
        }
    }
}