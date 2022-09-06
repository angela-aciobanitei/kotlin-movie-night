package com.ang.acb.movienight.ui.filter

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.ang.acb.movienight.R
import com.ang.acb.movienight.ui.common.*
import kotlinx.coroutines.FlowPreview

@FlowPreview
@Composable
fun FilterMoviesScreen(
    viewModel: FilterMoviesViewModel = hiltViewModel(),
    openMovieDetails: (movieId: Long) -> Unit
) {
    // todo@ang Content padding parameter it is not used
    Scaffold(
        topBar = {
            FilterMoviesTopBar(
                onFilterChanged = { viewModel.filter = it },
                filterLabel = viewModel.getFilterLabel()
            )
        },
        content = { padding ->
            val lazyPagingItems = viewModel.getPagedMovies(viewModel.filter).collectAsLazyPagingItems()

            LazyColumn (contentPadding = padding) {
                items(
                    items = lazyPagingItems,
                    // The key is important so the Lazy list can remember your
                    // scroll position when more items are fetched!
                    key = { item -> item.id }
                ) { item ->
                    if (item != null) {
                        MovieItem(
                            movie = item,
                            onMovieClick = { openMovieDetails(it) }
                        )
                    }
                }

                lazyPagingItems.apply {
                    when {
                        loadState.refresh is LoadState.Loading -> {
                            item {
                                PagingLoadingView(modifier = Modifier.fillParentMaxSize())
                            }
                        }

                        loadState.append is LoadState.Loading -> {
                            item {
                                PagingLoadingItem()
                            }
                        }

                        loadState.refresh is LoadState.Error -> {
                            val state = lazyPagingItems.loadState.refresh as LoadState.Error
                            item {
                                PagingErrorMessage(
                                    modifier = Modifier.fillParentMaxSize(),
                                    message = state.error.localizedMessage
                                        ?: stringResource(R.string.generic_error_message),
                                    onRetryClick = { retry() }
                                )
                            }
                        }

                        loadState.append is LoadState.Error -> {
                            val state = lazyPagingItems.loadState.append as LoadState.Error
                            item {
                                PagingErrorItem(
                                    message = state.error.localizedMessage
                                        ?: stringResource(R.string.generic_error_message),
                                    onRetryClick = { retry() }
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}