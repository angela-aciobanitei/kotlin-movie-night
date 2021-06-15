package com.ang.acb.movienight.ui.filtermovies

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.ang.acb.movienight.data.asStringResourceId
import com.ang.acb.movienight.domain.MovieFilter
import com.ang.acb.movienight.ui.state.ErrorItem
import com.ang.acb.movienight.ui.state.LoadingItem
import com.ang.acb.movienight.ui.state.LoadingView
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
                        onMovieClick = { /*TODO */ }
                    )
                }
            }

            lazyPagingItems.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                    }

                    loadState.append is LoadState.Loading -> {
                        item { LoadingItem() }
                    }

                    loadState.refresh is LoadState.Error -> {
                        val errorState = lazyPagingItems.loadState.refresh as LoadState.Error
                        item {
                            ErrorItem(
                                modifier = Modifier.fillParentMaxSize(),
                                message = errorState.error.localizedMessage!!,
                                onRetryClick = { retry() }
                            )
                        }
                    }
                    loadState.append is LoadState.Error -> {
                        val errorState = lazyPagingItems.loadState.append as LoadState.Error
                        item {
                            ErrorItem(
                                message = errorState.error.localizedMessage!!,
                                onRetryClick = { retry() }
                            )
                        }
                    }
                }
            }
        }
    }
}