package com.ang.acb.movienight.ui.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

        MovieTitleAndGenres(
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
private fun MovieTitleAndGenres(
    movieDetails: MovieDetails,
    isFavorite: Boolean,
    isFavoriteLoading: Boolean,
    onFavoriteClicked: (movie: Movie) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = movieDetails.movie.title ?: "",
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.W700)
        )

        if (isFavoriteLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                strokeWidth = 2.dp
            )
        } else {
            Icon(
                modifier = Modifier
                    .clickable { onFavoriteClicked(movieDetails.movie) }
                    .padding(16.dp),
                imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = null,
            )
        }

        FlowRow(
            mainAxisSpacing = 4.dp,
            crossAxisSpacing = 8.dp,
        ) {
            movieDetails.genres.forEach {
                if (it.name != null) GenreChip(genreName = it.name)
            }
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