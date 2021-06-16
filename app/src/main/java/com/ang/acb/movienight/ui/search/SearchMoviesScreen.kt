package com.ang.acb.movienight.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.ang.acb.movienight.domain.entities.Movie
import com.ang.acb.movienight.ui.common.ErrorItem
import com.ang.acb.movienight.ui.common.LoadingItem
import com.ang.acb.movienight.ui.common.LoadingView
import com.ang.acb.movienight.ui.common.MovieItem
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow

@ExperimentalComposeUiApi
@FlowPreview
@Composable
fun SearchMoviesScreen(
    viewModel: SearchMoviesViewModel,
    navController: NavHostController,
) {
    var query: String? by remember { mutableStateOf(null) }
    var searchResult: Flow<PagingData<Movie>>? by remember { mutableStateOf(null) }

    val textState = remember { mutableStateOf(TextFieldValue()) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = { SearchMoviesTopBar() }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            SearchMoviesTextField(
                textState = textState,
                onValueChange = { query = it },
                search = {
                    if (query.isNullOrBlank().not()) {
                        searchResult = viewModel.searchMovies(query!!.trim())
                    }
                }
            )

            searchResult?.let {
                val lazyPagingItems = it.collectAsLazyPagingItems()

                LazyColumn {
                    items(lazyPagingItems) { item ->
                        if (item != null) {
                            MovieItem(
                                movie = item,
                                onMovieClick = { movieId ->
                                    keyboardController?.hide()
                                    navController.navigate("movies/details/$movieId")
                                }
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
                                val state = lazyPagingItems.loadState.refresh as LoadState.Error
                                item {
                                    ErrorItem(
                                        modifier = Modifier.fillParentMaxSize(),
                                        message = state.error.localizedMessage!!,
                                        onRetryClick = { retry() }
                                    )
                                }
                            }
                            loadState.append is LoadState.Error -> {
                                val state = lazyPagingItems.loadState.append as LoadState.Error
                                item {
                                    ErrorItem(
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