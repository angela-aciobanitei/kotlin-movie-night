package com.ang.acb.movienight.ui.details

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ang.acb.movienight.R
import com.ang.acb.movienight.domain.entities.Trailer
import com.ang.acb.movienight.ui.common.LoadingBox
import com.ang.acb.movienight.ui.common.MessageBox
import com.ang.acb.movienight.utils.Constants.BACKDROP_URL
import com.ang.acb.movienight.utils.Constants.YOUTUBE_APP_BASE_URL
import com.ang.acb.movienight.utils.Constants.YOUTUBE_WEB_BASE_URL

@Composable
fun MovieDetailsScreen(
    viewModel: MovieDetailsViewModel = hiltViewModel(),
    openCastDetails: (castId: Long) -> Unit,
    openSimilarMovieDetails: (movieId: Long) -> Unit,
    upPressed: () -> Unit,
) {
    val scrollState = rememberScrollState()
    val context: Context = LocalContext.current

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

                        // Movie overview
                        if (movieDetails.movie.overview != null) {
                            MovieInfoHeader(title = stringResource(R.string.movie_details_overview_label))
                            Text(
                                "${movieDetails.movie.overview}",
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        // Movie cast
                        if (movieDetails.cast.isNotEmpty()) {
                            MovieInfoHeader(title = stringResource(R.string.movie_details_cast_label))
                            CastCarousel(
                                cast = movieDetails.cast,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(160.dp),
                                onItemClick = { castId ->
                                    openCastDetails(castId)
                                }
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        // Movie trailers
                        if (movieDetails.trailers.isNotEmpty()) {
                            MovieInfoHeader(title = stringResource(R.string.movie_details_trailers_label))
                            TrailerCarousel(
                                trailers = movieDetails.trailers,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(128.dp),
                                onItemClick = { trailer ->
                                    playVideo(trailer, context)
                                }
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        // Similar movies
                        if (viewModel.similarMovies.isNotEmpty()) {
                            MovieInfoHeader(title = stringResource(R.string.movie_details_similar_label))
                            SimilarMoviesCarousel(
                                movies = viewModel.similarMovies,
                                onItemClick = { openSimilarMovieDetails(it) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(160.dp),
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }
        }
    }
}

private fun playVideo(trailer: Trailer, context: Context) {
    // https://stackoverflow.com/questions/574195/android-youtube-app-play-video-intent
    val appIntent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse(YOUTUBE_APP_BASE_URL + trailer.key)
    )
    val webIntent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse(YOUTUBE_WEB_BASE_URL + trailer.key)
    )
    if (appIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(appIntent)
    } else {
        context.startActivity(webIntent)
    }
}