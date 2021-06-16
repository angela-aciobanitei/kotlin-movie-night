package com.ang.acb.movienight.ui.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun MovieDetailsScreen(
    navController: NavHostController,
    movieId: Long
) {

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Movie Details") }) }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Movie ID = $movieId")
        }
    }
}