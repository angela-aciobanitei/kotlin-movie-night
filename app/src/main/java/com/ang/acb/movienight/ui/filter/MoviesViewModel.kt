package com.ang.acb.movienight.ui.filter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ang.acb.movienight.domain.GetFilteredMoviesUseCase
import com.ang.acb.movienight.domain.Movie
import com.ang.acb.movienight.domain.MovieFilter
import com.ang.acb.movienight.domain.SaveFavoriteMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getFilteredMoviesUseCase: GetFilteredMoviesUseCase,
    private val saveFavoriteMovieUseCase: SaveFavoriteMovieUseCase,
) : ViewModel() {

    @FlowPreview
    fun getPagedMovies(filter: MovieFilter): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 50),
            pagingSourceFactory = {
                FilteredMoviesPagingSource(
                    getFilteredMoviesUseCase = getFilteredMoviesUseCase,
                    filter = filter
                )
            }
        ).flow
    }

    fun saveFavoriteMovie(movie: Movie) {
        viewModelScope.launch {
            val id = saveFavoriteMovieUseCase(movie)
            Timber.d("asd saved to db movie with id=$id")
        }
    }
}