package com.ang.acb.movienight.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ang.acb.movienight.domain.entities.Movie

@Composable
fun MovieItemDetails(
    movie: Movie,
    modifier: Modifier = Modifier,
) {
    // todo make it prettier
    Column(modifier.fillMaxWidth()) {
        val titleTextStyle = MaterialTheme.typography.h6
        val subtitleTextStyle = MaterialTheme.typography.overline
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = movie.title ?: "",
            maxLines = 2,
            style = titleTextStyle
        )

        Box(modifier = Modifier.width(16.dp))

        Text(
            text = "Release date: ${movie.releaseDate}",
            style = subtitleTextStyle
        )

        Text(
            text = "Vote average: ${movie.voteAverage}",
            style = subtitleTextStyle
        )

        Text(
            text = "Vote count: ${movie.voteCount}",
            style = subtitleTextStyle
        )
    }
}