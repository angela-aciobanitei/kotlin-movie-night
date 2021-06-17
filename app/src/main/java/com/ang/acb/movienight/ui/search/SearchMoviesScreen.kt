package com.ang.acb.movienight.ui.search

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.ang.acb.movienight.R
import com.ang.acb.movienight.domain.entities.Movie
import com.ang.acb.movienight.ui.common.MovieItem
import com.ang.acb.movienight.ui.common.PagingErrorItem
import com.ang.acb.movienight.ui.common.PagingLoadingItem
import com.ang.acb.movienight.ui.common.PagingLoadingView
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@FlowPreview
@Composable
fun SearchMoviesScreen(
    viewModel: SearchMoviesViewModel = hiltViewModel(),
    openMovieDetails: (movieId: Long) -> Unit
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    var searchQuery: TextFieldValue by remember { mutableStateOf(TextFieldValue("")) }
    var searchResult: Flow<PagingData<Movie>>? by remember { mutableStateOf(null) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = stringResource(R.string.search_movies_topbar_label)) })
        }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SearchMoviesTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                search = {
                    if (searchQuery.text.isNotBlank()) {
                        searchResult = viewModel.searchMovies(searchQuery.text.trim())
                    }
                }
            )

            // TODO Handle empty search results
            searchResult?.let {
                val lazyPagingItems = it.collectAsLazyPagingItems()

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
        }
    }
}