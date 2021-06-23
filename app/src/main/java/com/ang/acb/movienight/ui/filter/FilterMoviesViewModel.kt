package com.ang.acb.movienight.ui.filter

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ang.acb.movienight.data.source.local.asStringResId
import com.ang.acb.movienight.domain.entities.Movie
import com.ang.acb.movienight.domain.entities.MovieFilter
import com.ang.acb.movienight.domain.usecases.GetFilteredMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class FilterMoviesViewModel @Inject constructor(
    private val getFilteredMoviesUseCase: GetFilteredMoviesUseCase,
) : ViewModel() {

    var filter by mutableStateOf(MovieFilter.POPULAR)

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

    fun getFilterLabel() = filter.asStringResId()
}