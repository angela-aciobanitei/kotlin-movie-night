package com.ang.acb.movienight.ui.details

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ang.acb.movienight.domain.entities.Movie

@Composable
fun MovieInfoRating(
    movie: Movie
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Divider()

        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom,
        ) {

            if (movie.releaseDate.isNullOrEmpty().not()) {
                Icon(imageVector = Icons.Default.DateRange, contentDescription = null)
                Text(
                    text = movie.releaseDate!!,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
            }

            if (movie.voteCount != null) {
                Icon(imageVector = Icons.Default.People, contentDescription = null)
                Text(
                    text = "${movie.voteCount} votes",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
            }

            if (movie.voteAverage != null) {
                Icon(imageVector = Icons.Default.StarBorder, contentDescription = null)
                Text(
                    text = "${movie.voteAverage}",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }

        Divider()
    }
}