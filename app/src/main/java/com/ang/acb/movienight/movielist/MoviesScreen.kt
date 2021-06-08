package com.ang.acb.movienight.movielist

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.ang.acb.movienight.ui.theme.MovieNightTheme
import timber.log.Timber

@Composable
fun MoviesScreen(
    viewModel: MoviesViewModel = viewModel()
) {
    MovieNightTheme {
        Scaffold(topBar = { MoviesTopBar() }) {
            // TODO Handle lazy paging items load states: loading, error etc
            val lazyPagingItems = viewModel.moviesPaged.collectAsLazyPagingItems()
            Timber.d("asd ${lazyPagingItems.itemCount}")

            LazyColumn {
                items(viewModel.movies) { item ->
                    Timber.d("asd $item")
                    if (item != null) {
                        Text(item.title ?: "Invalid title")
                    }
                }
            }
        }
    }
}