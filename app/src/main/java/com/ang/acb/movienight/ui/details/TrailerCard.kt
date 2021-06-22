package com.ang.acb.movienight.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ang.acb.movienight.R
import com.ang.acb.movienight.domain.entities.Trailer
import com.ang.acb.movienight.utils.Constants.YOUTUBE_TRAILER_THUMBNAIL_BASE_URL
import com.ang.acb.movienight.utils.Constants.YOUTUBE_TRAILER_THUMBNAIL_HQ
import com.google.accompanist.glide.rememberGlidePainter
import com.google.accompanist.imageloading.ImageLoadState

@Composable
fun TrailerCard(
    trailer: Trailer,
    modifier: Modifier = Modifier,
    onItemClick: (trailer: Trailer) -> Unit,
) {
    Card(modifier = modifier) {
        Box(
            modifier = Modifier.clickable { onItemClick(trailer) },
            contentAlignment = Alignment.Center,
        ) {
            val thumbnail = YOUTUBE_TRAILER_THUMBNAIL_BASE_URL +
                    trailer.key + YOUTUBE_TRAILER_THUMBNAIL_HQ

            val painter = rememberGlidePainter(request = thumbnail, fadeIn = true)

            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .matchParentSize()
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop,
            )

            when (painter.loadState) {
                is ImageLoadState.Loading -> LoadingTrailer(modifier)
                is ImageLoadState.Error -> DefaultTrailer(modifier)
            }

            Icon(
                painter = painterResource(id = R.drawable.ic_play_circle_outline),
                contentDescription = null,
                tint = Color.Red,
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .scale(2f)
            )
        }
    }
}

@Composable
private fun DefaultTrailer(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.clip(MaterialTheme.shapes.medium),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_play_circle_outline),
            contentDescription = null,
            tint = Color.Red,
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .scale(2f)
        )
    }
}

@Composable
private fun LoadingTrailer(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.clip(MaterialTheme.shapes.medium),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(24.dp),
            strokeWidth = 2.dp
        )
    }
}

