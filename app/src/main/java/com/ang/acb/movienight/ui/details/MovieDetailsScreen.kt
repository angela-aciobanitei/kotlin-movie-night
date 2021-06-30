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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ang.acb.movienight.R
import com.ang.acb.movienight.domain.entities.Trailer
import com.ang.acb.movienight.ui.common.LoadingBox
import com.ang.acb.movienight.ui.common.MessageBox

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
                isFavorite = viewModel.isFavorite == true,
                isFavoriteLoading = viewModel.isFavoriteLoading,
                onFavoriteClicked = { viewModel.onFavoriteClicked() },
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
                        if (movieDetails.movie.backdropUrl != null) {
                            MovieBackdropImage(backdropUrl = movieDetails.movie.backdropUrl)
                        }

                        // Movie poster
                        MovieInfoPosterRow(movieDetails = movieDetails)

                        // Movie overview
                        if (movieDetails.movie.overview.isNullOrEmpty().not()) {
                            MovieInfoHeader(title = stringResource(R.string.movie_details_overview_label))
                            Text(
                                text = "${movieDetails.movie.overview}",
                                textAlign = TextAlign.Justify,
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        // Movie cast
                        if (movieDetails.cast.isNotEmpty()) {
                            MovieInfoHeader(title = stringResource(R.string.movie_details_cast_label))
                            CastCarousel(
                                cast = movieDetails.cast,
                                onItemClick = { castId -> openCastDetails(castId) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(160.dp),
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        // Movie trailers
                        if (movieDetails.trailers.isNotEmpty()) {
                            MovieInfoHeader(title = stringResource(R.string.movie_details_trailers_label))
                            TrailerCarousel(
                                trailers = movieDetails.trailers,
                                onItemClick = { trailer -> playVideo(trailer, context) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(128.dp),
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
    if (trailer.key != null) {
        val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse(trailer.youTubeAppUrl))
        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(trailer.youTubeWebUrl))

        if (appIntent.resolveActivity(context.packageManager) != null) {
            context.startActivity(appIntent)
        } else {
            context.startActivity(webIntent)
        }
    }
}