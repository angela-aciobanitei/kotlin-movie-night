package com.ang.acb.movienight.ui.searchmovies

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.ang.acb.movienight.domain.Movie
import com.ang.acb.movienight.ui.filtermovies.MovieItem
import com.ang.acb.movienight.ui.state.ErrorItem
import com.ang.acb.movienight.ui.state.LoadingItem
import com.ang.acb.movienight.ui.state.LoadingView
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow

@ExperimentalComposeUiApi
@FlowPreview
@Composable
fun SearchMoviesScreen(
    viewModel: SearchMoviesViewModel = hiltViewModel()
) {
    val textState = remember { mutableStateOf(TextFieldValue()) }
    var query: String? by remember { mutableStateOf(null) }
    var searchResult: Flow<PagingData<Movie>>? by remember { mutableStateOf(null) }


    // todo hide keyboard before navigating to movie details
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
                                val errorState =
                                    lazyPagingItems.loadState.refresh as LoadState.Error
                                item {
                                    ErrorItem(
                                        modifier = Modifier.fillParentMaxSize(),
                                        message = errorState.error.localizedMessage!!,
                                        onClickRetry = { retry() }
                                    )
                                }
                            }
                            loadState.append is LoadState.Error -> {
                                val e = lazyPagingItems.loadState.append as LoadState.Error
                                item {
                                    ErrorItem(
                                        message = e.error.localizedMessage!!,
                                        onClickRetry = { retry() }
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