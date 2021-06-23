package com.ang.acb.movienight.ui.search

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.ang.acb.movienight.domain.entities.Movie
import com.ang.acb.movienight.ui.common.MovieItem
import com.ang.acb.movienight.ui.common.PagingErrorItem
import com.ang.acb.movienight.ui.common.PagingLoadingItem
import com.ang.acb.movienight.ui.common.PagingLoadingView
import kotlinx.coroutines.flow.Flow

@ExperimentalComposeUiApi
@Composable
fun SearchMoviesResults(
    searchResults: Flow<PagingData<Movie>>,
    openMovieDetails: (movieId: Long) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    val lazyPagingItems = searchResults.collectAsLazyPagingItems()

    LazyColumn {
        items(lazyPagingItems) { item ->
            if (item != null) {
                MovieItem(
                    movie = item,
                    onMovieClick = { movieId ->
                        keyboardController?.hide()
                        openMovieDetails(movieId)
                    }
                )
            }
        }

        lazyPagingItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { PagingLoadingView(modifier = Modifier.fillParentMaxSize()) }
                }

                loadState.append is LoadState.Loading -> {
                    item { PagingLoadingItem() }
                }

                loadState.refresh is LoadState.Error -> {
                    val state = lazyPagingItems.loadState.refresh as LoadState.Error
                    item {
                        PagingErrorItem(
                            modifier = Modifier.fillParentMaxSize(),
                            message = state.error.localizedMessage!!,
                            onRetryClick = { retry() }
                        )
                    }
                }
                loadState.append is LoadState.Error -> {
                    val state = lazyPagingItems.loadState.append as LoadState.Error
                    item {
                        PagingErrorItem(
                            message = state.error.localizedMessage!!,
                            onRetryClick = { retry() }
                        )
                    }
                }
            }
        }
    }
}