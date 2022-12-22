package com.ang.acb.movienight.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ang.acb.movienight.domain.entities.Trailer
import com.ang.acb.movienight.ui.theme.midnight50

@Composable
fun TrailerCard(
    trailer: Trailer,
    modifier: Modifier = Modifier,
    onItemClick: (trailer: Trailer) -> Unit,
) {
    Card(modifier = modifier) {
        Box(
            modifier = Modifier
                .clickable { onItemClick(trailer) }
                .background(
                    shape = MaterialTheme.shapes.medium,
                    color = midnight50,
                ),
            contentAlignment = Alignment.Center,
        ) {
            // TODO Use a placeholder on error
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(trailer.youTubeThumbnailUrl)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize(),
            )
        }
    }
}

