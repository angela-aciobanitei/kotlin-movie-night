package com.ang.acb.movienight.ui.movielist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.ang.acb.movienight.ui.theme.MovieNightTheme

@ExperimentalFoundationApi
@Composable
fun MoviesScreen(
    viewModel: MoviesViewModel = viewModel()
) {
    MovieNightTheme {
        Scaffold(topBar = { MoviesTopBar(viewModel) }) {
            // TODO Handle lazy paging items load states: loading, error etc
            val lazyPagingItems = viewModel.getPagedStuff().collectAsLazyPagingItems()

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