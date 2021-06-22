package com.ang.acb.movienight.ui.details

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ang.acb.movienight.domain.entities.Trailer
import com.ang.acb.movienight.ui.common.Carousel

@Composable
fun TrailerCarousel(
    trailers: List<Trailer>,
    modifier: Modifier = Modifier,
    onItemClick: (trailer: Trailer) -> Unit,
) {
    Carousel(
        items = trailers,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        itemSpacing = 4.dp,
        modifier = modifier
    ) { item, padding ->
        TrailerCard(
            trailer = item,
            modifier = Modifier
                .padding(padding)
                .fillParentMaxHeight()
                .aspectRatio(16 / 9f),
            onItemClick = onItemClick
        )
    }
}