package com.ang.acb.movienight.ui.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ang.acb.movienight.domain.entities.Genre
import com.ang.acb.movienight.domain.entities.Movie
import com.ang.acb.movienight.domain.entities.MovieDetails
import com.ang.acb.movienight.ui.common.MoviePoster
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun MovieInfoPosterRow(
    movieDetails: MovieDetails,
    isFavorite: Boolean,
    isFavoriteLoading: Boolean,
    onFavoriteClicked: (movie: Movie) -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        MoviePoster(
            posterUrl = movieDetails.movie.posterUrl,
            modifier = Modifier
                .weight(2f)
                .aspectRatio(2 / 3f)
        )

        MovieInfo(
            movieDetails = movieDetails,
            isFavorite = isFavorite,
            isFavoriteLoading = isFavoriteLoading,
            onFavoriteClicked = onFavoriteClicked,
            modifier = Modifier
                .weight(3f)
                .padding(start = 16.dp)
        )
    }
}

@Composable
private fun MovieInfo(
    movieDetails: MovieDetails,
    isFavorite: Boolean,
    isFavoriteLoading: Boolean,
    onFavoriteClicked: (movie: Movie) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier.fillMaxWidth()) {
        MovieTitleRow(
            movie = movieDetails.movie,
            isFavorite = isFavorite,
            isFavoriteLoading = isFavoriteLoading,
            onFavoriteClicked = onFavoriteClicked,
        )

        GenresFlowRow(genres = movieDetails.genres)
    }
}

@Composable
private fun MovieTitleRow(
    movie: Movie,
    isFavorite: Boolean,
    isFavoriteLoading: Boolean,
    onFavoriteClicked: (movie: Movie) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
        verticalAlignment = Alignment.Top,
    ) {
        Box(modifier = Modifier.weight(4f)) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = movie.title ?: "",
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.W700)
            )
        }

        Box(modifier = Modifier.weight(1f)) {
            if (isFavoriteLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.CenterEnd),
                    strokeWidth = 2.dp
                )
            } else {
                Icon(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .scale(1.5f)
                        .clickable { onFavoriteClicked(movie) },
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = null,
                )
            }
        }
    }
}

@Composable
private fun GenresFlowRow(genres: List<Genre>) {
    FlowRow(
        mainAxisSpacing = 4.dp,
        crossAxisSpacing = 8.dp,
    ) {
        genres.forEach {
            if (it.name != null) GenreChip(genreName = it.name)
        }
    }
}

@Composable
private fun GenreChip(genreName: String) {
    Surface(
        elevation = 4.dp,
        shape = RoundedCornerShape(70),
        color = MaterialTheme.colors.primary
    ) {
        Text(
            text = genreName,
            style = MaterialTheme.typography.body2,
            color = Color.White,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
        )
    }
}