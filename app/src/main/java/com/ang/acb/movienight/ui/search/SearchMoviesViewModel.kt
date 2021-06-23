package com.ang.acb.movienight.ui.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ang.acb.movienight.domain.entities.Movie
import com.ang.acb.movienight.domain.usecases.SearchMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

@FlowPreview
@HiltViewModel
class SearchMoviesViewModel @Inject constructor(
    private val searchMoviesUseCase: SearchMoviesUseCase,
) : ViewModel() {

    val searchQuery = MutableStateFlow("")
    var searchResults: Flow<PagingData<Movie>>? by mutableStateOf(null)
    private var currentJob: Job? = null

    init {
        viewModelScope.launch {
            searchQuery.debounce(300)
                .collectLatest { query ->
                    currentJob?.cancel()
                    currentJob = launch {
                        searchResults = if (query.isNotBlank()) search(query.trim()) else null
                    }
                }
        }
    }

    private fun search(query: String): Flow<PagingData<Movie>> {
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

    fun updateQuery(query: String) {
        searchQuery.value = query
    }
}