package com.ang.acb.movienight.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ang.acb.movienight.domain.entities.Movie
import com.ang.acb.movienight.utils.Constants.POSTER_URL

@Composable
fun MovieItem(
    movie: Movie,
    onMovieClick: (movieId: Long) -> Unit
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { onMovieClick(movie.id) }
        .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        if (movie.posterPath == null) {
            DefaultMoviePoster()
        } else {
            MoviePoster(posterUrl = POSTER_URL + movie.posterPath)
        }

        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = movie.title ?: "",
                maxLines = 2,
                style = TextStyle(fontWeight = FontWeight.W700, fontSize = 18.sp)
            )

            Box(modifier = Modifier.width(16.dp))

            Text(
                text = "Release date: ${movie.releaseDate}",
                style = TextStyle(fontSize = 12.sp)
            )

            Text(
                text = "Vote average: ${movie.voteAverage}",
                style = TextStyle(fontSize = 12.sp)
            )
        }
    }
}