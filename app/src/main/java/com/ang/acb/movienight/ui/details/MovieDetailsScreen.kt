package com.ang.acb.movienight.ui.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ang.acb.movienight.ui.common.LoadingBox
import com.ang.acb.movienight.ui.common.MessageBox
import com.ang.acb.movienight.utils.Constants.BACKDROP_URL

@Composable
fun MovieDetailsScreen(
    viewModel: MovieDetailsViewModel = hiltViewModel(),
    upPressed: () -> Unit
) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            val title = viewModel.movieDetails?.movie?.title
            MovieDetailsTopBar(
                title = title ?: "",
                upPressed = upPressed
            )
        }
    ) {
        if (viewModel.isLoading) {
            LoadingBox()
        } else {
            if (viewModel.errorMessage != null) {
                MessageBox(messageResId = viewModel.errorMessage!!)
            } else {
                Column(Modifier.verticalScroll(scrollState)) {
                    viewModel.movieDetails?.let { movieDetails ->
                        // Movie backdrop image
                        if (movieDetails.movie.backdropPath != null) {
                            MovieBackdropImage(backdropUrl = BACKDROP_URL + movieDetails.movie.backdropPath)
                        }
                        Box(modifier = Modifier
                            .height(16.dp)
                            .fillMaxWidth())

                        // Movie poster
                        MoviePosterInfo(movieDetails = movieDetails)
                        Box(modifier = Modifier
                            .height(16.dp)
                            .fillMaxWidth())

                        // TODO Movie overview
                        val modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        val titleStyle = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.W700)
                        Text("Overview", modifier = modifier, style = titleStyle)
                        Text("${movieDetails.movie.overview}", modifier = modifier)

                        // TODO Movie cast
                        Text("Cast", modifier = modifier, style = titleStyle)
                        Text("${movieDetails.cast.map { it.actorName }}", modifier = modifier)

                        // TODO Movie trailers
                        Text("Trailers", modifier = modifier, style = titleStyle)
                        Text("${movieDetails.trailers.map { it.name }}", modifier = modifier)

                    }

                }
            }
        }
    }
}