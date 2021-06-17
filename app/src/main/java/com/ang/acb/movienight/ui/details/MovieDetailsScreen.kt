package com.ang.acb.movienight.ui.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun MovieDetailsScreen(
    movieId: Long,
    upPressed: () -> Unit
) {
    Scaffold(
        topBar = {
            MovieDetailsTopBar(
                title = "Movie Details",
                upPressed = upPressed
            )
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Movie ID = $movieId")
        }
    }
}