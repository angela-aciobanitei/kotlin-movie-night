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
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.ang.acb.movienight.ui.filtermovies.MovieItem
import kotlinx.coroutines.FlowPreview

@ExperimentalComposeUiApi
@FlowPreview
@Composable
fun SearchMoviesScreen(
    viewModel: SearchMoviesViewModel = hiltViewModel()
) {
    var query: String? by remember { mutableStateOf(null) }
    val textState = remember { mutableStateOf(TextFieldValue()) }

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


                }
            )

            // todo fix search
            val lazyPagingItems = viewModel.searchMovies("star").collectAsLazyPagingItems()

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