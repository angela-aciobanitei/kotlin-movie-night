package com.ang.acb.movienight.ui.search

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.ang.acb.movienight.R
import com.ang.acb.movienight.domain.entities.Movie
import com.ang.acb.movienight.ui.common.*
import kotlinx.coroutines.flow.Flow

@Composable
fun SearchMoviesResults(
    searchTerm: String,
    searchResults: Flow<PagingData<Movie>>,
    onItemClick: (movieId: Long) -> Unit,
) {
    val lazyPagingItems = searchResults.collectAsLazyPagingItems()

    LazyColumn {
        items(
            items = lazyPagingItems,
            key = { item -> item.id }
        ) { item ->
            if (item != null) {
                MovieItem(
                    movie = item,
                    onMovieClick = onItemClick
                )
            }
        }

        lazyPagingItems.apply {
            when (loadState.refresh) {
                is LoadState.Loading -> {
                    item {
                        PagingLoadingView(modifier = Modifier.fillParentMaxSize())
                    }
                }

                is LoadState.Error -> {
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

                LoadState.NotLoading(endOfPaginationReached = true) -> {
                    if (lazyPagingItems.itemCount == 0) {
                        item {
                            SearchResultEmptyMessage(searchTerm)
                        }
                    }
                }
                else -> {}
            }

            when (loadState.append) {
                is LoadState.Loading -> {
                    item {
                        PagingLoadingItem()
                    }
                }

                is LoadState.Error -> {
                    val state = lazyPagingItems.loadState.append as LoadState.Error
                    item {
                        PagingErrorItem(
                            message = state.error.localizedMessage
                                ?: stringResource(R.string.generic_error_message),
                            onRetryClick = { retry() }
                        )
                    }
                }

                LoadState.NotLoading(endOfPaginationReached = true) -> {
                    if (lazyPagingItems.itemCount == 0) {
                        item {
                            SearchResultEmptyMessage(searchTerm)
                        }
                    }
                }
                else -> {}
            }
        }
    }
}