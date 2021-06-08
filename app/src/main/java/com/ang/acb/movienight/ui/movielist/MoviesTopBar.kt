package com.ang.acb.movienight.ui.movielist

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.ang.acb.movienight.R

@Composable
fun MoviesTopBar() {
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.movies_topbar_label_popular)) // todo this should change according to the filter
        },
    )
}