package com.ang.acb.movienight.ui.movielist

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.ang.acb.movienight.data.asStringResourceId
import com.ang.acb.movienight.domain.MovieFilter
import com.ang.acb.movienight.ui.theme.MovieNightTheme
import kotlinx.coroutines.FlowPreview

@FlowPreview
@Composable
fun MoviesScreen(
    viewModel: MoviesViewModel = viewModel()
) {
    var filter by remember { mutableStateOf(MovieFilter.POPULAR) }

    MovieNightTheme {
        Scaffold(
            topBar = {
                MoviesTopBar(
                    onFilterChanged = { filter = it },
                    filterLabel = filter.asStringResourceId()
                )
            }
        ) {
            // TODO Handle lazy paging items load states: loading, error etc
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
            }
        }
    }
}