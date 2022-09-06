package com.ang.acb.movienight.ui.details

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ang.acb.movienight.domain.entities.Cast
import com.ang.acb.movienight.ui.common.Carousel

@Composable
fun CastCarousel(
    cast: List<Cast>,
    onItemClick: (cast: Cast) -> Unit,
    modifier: Modifier = Modifier,
) {
    Carousel(
        items = cast,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        itemSpacing = 4.dp,
        modifier = modifier
    ) { item, padding ->
        CastCard(
            cast = item,
            onItemClick = onItemClick,
            modifier = Modifier
                .padding(padding)
                .fillParentMaxHeight()
                .aspectRatio(2 / 3f),
        )
    }
}