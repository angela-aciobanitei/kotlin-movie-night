package com.ang.acb.movienight.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ang.acb.movienight.R
import com.ang.acb.movienight.domain.entities.Trailer
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
            modifier = Modifier
                .clickable { onItemClick(trailer) }
                .background(
                    shape = MaterialTheme.shapes.medium,
                    color = Color.LightGray, // todo pick a nicer color
                ),
            contentAlignment = Alignment.Center,
        ) {
            val painter = rememberGlidePainter(request = trailer.youTubeThumbnailUrl, fadeIn = true)

            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize(),
            )

            when (painter.loadState) {
                is ImageLoadState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(24.dp),
                        strokeWidth = 2.dp
                    )
                }
                else -> {
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
    }
}

