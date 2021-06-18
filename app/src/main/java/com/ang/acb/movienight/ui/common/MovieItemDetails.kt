package com.ang.acb.movienight.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ang.acb.movienight.domain.entities.Movie

@Composable
fun MovieItemDetails(
    movie: Movie,
    modifier: Modifier = Modifier,
) {
    // todo make it prettier
    Column(modifier.fillMaxWidth()) {
        val titleTextStyle = TextStyle(fontWeight = FontWeight.W700, fontSize = 18.sp)
        val subtitleTextStyle = MaterialTheme.typography.caption
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