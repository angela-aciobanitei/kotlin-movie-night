package com.ang.acb.movienight.ui.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ang.acb.movienight.domain.entities.Movie
import com.ang.acb.movienight.domain.usecases.SearchMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@FlowPreview
@HiltViewModel
class SearchMoviesViewModel @Inject constructor(
    private val searchMoviesUseCase: SearchMoviesUseCase,
) : ViewModel() {

    var searchQuery: TextFieldValue by mutableStateOf(TextFieldValue(""))
    var searchResults: Flow<PagingData<Movie>>? by mutableStateOf(null)

    @FlowPreview
    fun searchMovies(query: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 50),
            pagingSourceFactory = {
                SearchMoviesPagingSource(
                    query = query,
                    searchMoviesUseCase = searchMoviesUseCase,
                )
            }
        ).flow
    }

    fun doSearch() {
        if (searchQuery.text.isNotBlank()) {
            searchResults = searchMovies(searchQuery.text.trim())
        }
    }
}