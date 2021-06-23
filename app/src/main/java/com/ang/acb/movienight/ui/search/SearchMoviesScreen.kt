package com.ang.acb.movienight.ui.search

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.ang.acb.movienight.R
import kotlinx.coroutines.FlowPreview

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@FlowPreview
@Composable
fun SearchMoviesScreen(
    viewModel: SearchMoviesViewModel = hiltViewModel(),
    openMovieDetails: (movieId: Long) -> Unit
) {
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
                value = viewModel.searchQuery,
                onValueChange = { viewModel.searchQuery = it },
                onSearch = { viewModel.doSearch() }
            )

            // TODO Handle empty search results
            viewModel.searchResults?.let {
                SearchMoviesResults(
                    searchResults = it,
                    openMovieDetails = openMovieDetails
                )
            }
        }
    }
}