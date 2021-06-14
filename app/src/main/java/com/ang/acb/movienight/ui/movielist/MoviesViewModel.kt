package com.ang.acb.movienight.ui.movielist

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ang.acb.movienight.data.MoviesPagingSource
import com.ang.acb.movienight.domain.GetFilteredMoviesUseCase
import com.ang.acb.movienight.domain.Movie
import com.ang.acb.movienight.domain.MovieFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getFilteredMoviesUseCase: GetFilteredMoviesUseCase,
) : ViewModel() {

    @FlowPreview
    fun getPagedMovies(filter: MovieFilter): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 50),
            pagingSourceFactory = {
                MoviesPagingSource(
                    getFilteredMoviesUseCase = getFilteredMoviesUseCase,
                    filter = filter
                )
            }
        ).flow
    }
}