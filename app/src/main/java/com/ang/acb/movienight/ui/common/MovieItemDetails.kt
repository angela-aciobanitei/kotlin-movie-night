package com.ang.acb.movienight.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ang.acb.movienight.R
import com.ang.acb.movienight.domain.entities.Movie
import com.ang.acb.movienight.ui.theme.moviesFontFamily

@Composable
fun MovieItemDetails(
    movie: Movie,
    modifier: Modifier = Modifier,
) {
    Column(modifier.fillMaxWidth()) {
        val titleTextStyle = TextStyle(
            fontFamily = moviesFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            letterSpacing = 0.15.sp,
        )
        val subtitleTextStyle = MaterialTheme.typography.caption
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = movie.title ?: "",
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = titleTextStyle
        )

        Spacer(modifier = Modifier.width(16.dp))

        if (movie.releaseDate != null) {
            Text(
                text = stringResource(
                    id = R.string.movie_details_release_date,
                    formatArgs = arrayOf(movie.releaseDate)
                ),
                style = subtitleTextStyle
            )
        }

        if (movie.voteAverage != null) {
            Text(
                text = stringResource(
                    id = R.string.movie_details_vote_average,
                    formatArgs = arrayOf(movie.voteAverage)
                ),
                style = subtitleTextStyle
            )
        }

        if (movie.voteCount != null) {
            Text(
                text = stringResource(
                    id = R.string.movie_details_vote_count,
                    formatArgs = arrayOf(movie.voteCount)
                ),
                style = subtitleTextStyle
            )
        }
    }
}