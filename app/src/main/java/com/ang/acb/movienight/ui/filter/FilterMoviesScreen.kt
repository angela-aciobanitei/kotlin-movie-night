package com.ang.acb.movienight.ui.filter

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.ang.acb.movienight.R
import com.ang.acb.movienight.data.source.local.asStringResId
import com.ang.acb.movienight.domain.entities.MovieFilter
import com.ang.acb.movienight.ui.common.*
import kotlinx.coroutines.FlowPreview

@FlowPreview
@Composable
fun FilterMoviesScreen(
    viewModel: FilterMoviesViewModel = hiltViewModel(),
    openMovieDetails: (movieId: Long) -> Unit
) {
    var filter by remember { mutableStateOf(MovieFilter.POPULAR) }

    Scaffold(
        topBar = {
            FilterMoviesTopBar(
                onFilterChanged = { filter = it },
                filterLabel = filter.asStringResId()
            )
        }
    ) {
        val lazyPagingItems = viewModel.getPagedMovies(filter).collectAsLazyPagingItems()

        LazyColumn {
            items(lazyPagingItems) { item ->
                if (item != null) {
                    MovieItem(
                        movie = item,
                        onMovieClick = { openMovieDetails(it) }
                    )
                }
            }

            lazyPagingItems.apply {
                when {
                    // Handle loading states
                    loadState.refresh is LoadState.Loading -> {
                        item { PagingLoadingView(modifier = Modifier.fillParentMaxSize()) }
                    }

                    loadState.append is LoadState.Loading -> {
                        item { PagingLoadingItem() }
                    }

                    // Handle error states
                    // TODO Parse error response to get status message
                    // {"status_code":7,"status_message":"Invalid API key.","success":false}
                    loadState.refresh is LoadState.Error -> {
                        val errorState = lazyPagingItems.loadState.refresh as LoadState.Error
                        item {
                            PagingErrorMessage(
                                modifier = Modifier.fillParentMaxSize(),
                                message = errorState.error.message
                                    ?: stringResource(R.string.generic_error_message),
                                onRetryClick = { retry() }
                            )
                        }
                    }
                    loadState.append is LoadState.Error -> {
                        val errorState = lazyPagingItems.loadState.append as LoadState.Error
                        item {
                            PagingErrorItem(
                                message = errorState.error.message
                                    ?: stringResource(R.string.generic_error_message),
                                onRetryClick = { retry() }
                            )
                        }
                    }
                }
            }
        }
    }
}