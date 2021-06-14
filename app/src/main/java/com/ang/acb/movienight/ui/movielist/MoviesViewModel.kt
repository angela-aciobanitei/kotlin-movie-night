package com.ang.acb.movienight.ui.movielist

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
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapMerge
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getFilteredMoviesUseCase: GetFilteredMoviesUseCase,
) : ViewModel() {

    private val _movieFilter: MutableStateFlow<MovieFilter> = MutableStateFlow(MovieFilter.POPULAR)
    val movieFilter: StateFlow<MovieFilter> = _movieFilter

    var title = mutableStateOf(R.string.filter_by_popular)
    var filter = mutableStateOf(MovieFilter.POPULAR)

    @FlowPreview
    fun getPagedStuff(): Flow<PagingData<Movie>> {
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
        _movieFilter.value = filter
    }

    private fun updateTitle(filter: MovieFilter) {
        when (filter) {
            MovieFilter.POPULAR -> title.value = R.string.filter_by_popular
            MovieFilter.TOP_RATED -> title.value = R.string.filter_by_top_rated
            MovieFilter.NOW_PLAYING -> title.value = R.string.filter_by_now_playing
            MovieFilter.UPCOMING -> title.value = R.string.filter_by_upcoming
        }
    }

}