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
import com.ang.acb.movienight.data.source.local.asStringResourceId
import com.ang.acb.movienight.domain.entities.MovieFilter
import com.ang.acb.movienight.ui.common.*
import kotlinx.coroutines.FlowPreview

@FlowPreview
@Composable
fun MoviesScreen(
    viewModel: MoviesViewModel = hiltViewModel()
) {
    var filter by remember { mutableStateOf(MovieFilter.POPULAR) }

    Scaffold(
        topBar = {
            MoviesTopBar(
                onFilterChanged = { filter = it },
                filterLabel = filter.asStringResourceId()
            )
        }
    ) {
        val lazyPagingItems = viewModel.getPagedMovies(filter).collectAsLazyPagingItems()

        LazyColumn {
            items(lazyPagingItems) { item ->
                if (item != null) {
                    MovieItem(
                        movie = item,
                        onMovieClick = {
                            // TODO Go to movie details page on click
                            viewModel.saveFavoriteMovie(it)
                            viewModel.getMovieDetails(it.id)
                        }
                    )
                }
            }

            lazyPagingItems.apply {
                when {
                    // Handle loading states
                    // See: https://developer.android.com/reference/kotlin/androidx/paging/compose/package-summary#collectaslazypagingitems
                    loadState.refresh is LoadState.Loading -> {
                        item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                    }

                    loadState.append is LoadState.Loading -> {
                        item { LoadingItem() }
                    }

                    // Handle error states
                    // TODO Parse error response to get status message
                    // {"status_code":7,"status_message":"Invalid API key.","success":false}
                    loadState.refresh is LoadState.Error -> {
                        val errorState = lazyPagingItems.loadState.refresh as LoadState.Error
                        item {
                            ErrorMessage(
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
                            ErrorItem(
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