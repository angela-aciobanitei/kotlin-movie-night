package com.ang.acb.movienight.ui.movielist

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ang.acb.movienight.R
import com.ang.acb.movienight.data.MoviesPagingSource
import com.ang.acb.movienight.domain.GetFilteredMoviesUseCase
import com.ang.acb.movienight.domain.Movie
import com.ang.acb.movienight.domain.MovieFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapMerge
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getFilteredMoviesUseCase: GetFilteredMoviesUseCase,
) : ViewModel() {

    private val movieFilter = MutableStateFlow(MovieFilter.POPULAR)
    var title: MutableState<Int> = mutableStateOf(R.string.filter_by_popular)

    @FlowPreview
    fun getPagedMovies(): Flow<PagingData<Movie>> {
        return movieFilter.flatMapMerge {
            Pager(
                config = PagingConfig(enablePlaceholders = false, pageSize = 50),
                pagingSourceFactory = {
                    MoviesPagingSource(
                        getFilteredMoviesUseCase = getFilteredMoviesUseCase,
                        filter = it
                    )
                }
            ).flow
        }
    }

    fun onFilterChanged(filter: MovieFilter) {
        updateFilter(filter)
        updateTitle(filter)
    }

    private fun updateFilter(filter: MovieFilter) {
        movieFilter.value = filter
    }

    private fun updateTitle(filter: MovieFilter) {
        title.value = when (filter) {
            MovieFilter.POPULAR -> R.string.filter_by_popular
            MovieFilter.TOP_RATED -> R.string.filter_by_top_rated
            MovieFilter.NOW_PLAYING -> R.string.filter_by_now_playing
            MovieFilter.UPCOMING -> R.string.filter_by_upcoming
        }
    }
}