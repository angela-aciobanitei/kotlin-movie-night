package com.ang.acb.movienight.ui.searchmovies

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.ang.acb.movienight.R

@Composable
fun SearchMoviesTopBar() {
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.search_movies_topbar_label))
        },
    )
}