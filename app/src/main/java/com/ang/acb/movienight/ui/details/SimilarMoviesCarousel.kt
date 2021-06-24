package com.ang.acb.movienight.ui.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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

@Composable
fun SimilarMoviesCarousel(
    movies: List<Movie>,
    modifier: Modifier = Modifier,
    onItemClick: (movieId: Long) -> Unit,
) {
    Carousel(
        items = movies,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        itemSpacing = 4.dp,
        modifier = modifier
    ) { item, padding ->
        Card(modifier = modifier) {
            Box(modifier = Modifier.clickable { onItemClick(item.id) }) {
                MoviePoster(
                    posterUrl = item.posterUrl,
                    modifier = Modifier
                        .padding(padding)
                        .fillParentMaxHeight()
                        .aspectRatio(2 / 3f),
                )
            }
        }
    }
}