package com.ang.acb.movienight.ui.details

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ang.acb.movienight.domain.entities.Movie
import com.ang.acb.movienight.ui.common.Carousel
import com.ang.acb.movienight.ui.common.MoviePoster
import com.ang.acb.movienight.utils.Constants

@Composable
fun SimilarMoviesCarousel(
    movies: List<Movie>,
    modifier: Modifier = Modifier,
) {
    Carousel(
        items = movies,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        itemSpacing = 4.dp,
        modifier = modifier
    ) { item, padding ->
        Card(modifier = modifier) {
            MoviePoster(
                posterUrl = Constants.POSTER_URL + item.posterPath,
                modifier = Modifier
                    .padding(padding)
                    .fillParentMaxHeight()
                    .aspectRatio(2 / 3f),
            )
        }
    }
}