package com.ang.acb.movienight.movielist

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun MoviesTopBar() {
    TopAppBar(
        title = {
            Text(text = "Movies") // todo this should change according to the filter
        },
    )
}