package com.ang.acb.movienight.ui.filter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ang.acb.movienight.domain.entities.Movie
import com.ang.acb.movienight.domain.entities.MovieFilter
import com.ang.acb.movienight.domain.usecases.GetFilteredMoviesUseCase
import com.ang.acb.movienight.domain.usecases.GetMovieDetailsUseCase
import com.ang.acb.movienight.domain.usecases.SaveFavoriteMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FilterMoviesViewModel @Inject constructor(
    private val getFilteredMoviesUseCase: GetFilteredMoviesUseCase,
    private val saveFavoriteMovieUseCase: SaveFavoriteMovieUseCase,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
) : ViewModel() {

    @FlowPreview
    fun getPagedMovies(filter: MovieFilter): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 50),
            pagingSourceFactory = {
                FilterMoviesPagingSource(
                    getFilteredMoviesUseCase = getFilteredMoviesUseCase,
                    filter = filter
                )
            }
        ).flow
    }

    // TODO This method is here for testing purposes
    fun saveFavoriteMovie(movie: Movie) {
        viewModelScope.launch {
            val id = saveFavoriteMovieUseCase(movie)
            Timber.d("asd saved to db movie with id=$id")
        }
    }

    // TODO This method is here for testing purposes
    fun getMovieDetails(movieId: Long) {
        viewModelScope.launch {
            val movie = getMovieDetailsUseCase(movieId)
            Timber.d("asd movie=$movie")
        }
    }
}