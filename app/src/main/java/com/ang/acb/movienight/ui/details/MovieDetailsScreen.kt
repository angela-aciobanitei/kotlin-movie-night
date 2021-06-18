package com.ang.acb.movienight.ui.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ang.acb.movienight.R
import com.ang.acb.movienight.ui.common.LoadingBox
import com.ang.acb.movienight.ui.common.MessageBox
import com.ang.acb.movienight.utils.Constants.BACKDROP_URL

@Composable
fun MovieDetailsScreen(
    viewModel: MovieDetailsViewModel = hiltViewModel(),
    openCastDetails: (movieId: Long, castId: Long) -> Unit,
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

                        // Movie poster
                        MovieInfoPosterRow(movieDetails = movieDetails)
                        Spacer(modifier = Modifier.height(16.dp))

                        // TODO Movie overview
                        MovieInfoHeader(title = stringResource(R.string.movie_details_overview_label))
                        Text(
                            "${movieDetails.movie.overview}",
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        // Movie cast
                        MovieInfoHeader(title = stringResource(R.string.movie_details_cast_label))
                        CastCarousel(
                            cast = movieDetails.cast,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(160.dp),
                            onItemClick = { movieId, castId ->
                                openCastDetails(movieId, castId)
                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        // TODO Movie trailers
                        MovieInfoHeader(title = stringResource(R.string.movie_details_trailers_label))
                        Text(
                            "${movieDetails.trailers.map { it.name }}",
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}