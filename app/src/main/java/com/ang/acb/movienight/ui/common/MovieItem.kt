package com.ang.acb.movienight.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ang.acb.movienight.domain.entities.Movie

@Composable
fun MovieItem(
    movie: Movie,
    onMovieClick: (movieId: Long) -> Unit
) {
    Row(
        modifier = Modifier
            .clickable { onMovieClick(movie.id) }
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
    ) {
        MoviePoster(
            posterUrl = movie.posterUrl,
            modifier = Modifier
                .weight(1f)
                .aspectRatio(2 / 3f)
        )

        MovieItemDetails(
            movie = movie,
            modifier = Modifier
                .weight(4f)
                .padding(horizontal = 16.dp)
        )
    }
}