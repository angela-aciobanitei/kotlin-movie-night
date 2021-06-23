package com.ang.acb.movienight.ui.search

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.ang.acb.movienight.R
import kotlinx.coroutines.FlowPreview

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@FlowPreview
@Composable
fun SearchMoviesScreen(
    viewModel: SearchMoviesViewModel = hiltViewModel(),
    openMovieDetails: (movieId: Long) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var query by remember { mutableStateOf(TextFieldValue(viewModel.searchQuery.value)) }

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
                value = query,
                onValueChange = { value ->
                    query = value
                    viewModel.updateQuery(value.text)
                },
            )

            // TODO Handle empty search results
            viewModel.searchResults?.let {
                SearchMoviesResults(
                    searchResults = it,
                    onItemClick = { movieId ->
                        keyboardController?.hide()
                        openMovieDetails(movieId)
                    }
                )
            }
        }
    }
}