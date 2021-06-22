package com.ang.acb.movienight.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ang.acb.movienight.R
import com.ang.acb.movienight.ui.theme.Purple700
import com.google.accompanist.glide.rememberGlidePainter
import com.google.accompanist.imageloading.ImageLoadState

@Composable
fun MoviePoster(
    posterUrl: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.clip(MaterialTheme.shapes.medium),
        contentAlignment = Alignment.Center,
    ) {
        val painter = rememberGlidePainter(request = posterUrl)

        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop,
        )

        when (painter.loadState) {
            is ImageLoadState.Loading -> LoadingMoviePoster(modifier)
            is ImageLoadState.Error -> DefaultMoviePoster(modifier)
        }
    }
}

@Composable
fun DefaultMoviePoster(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.background(
            shape = MaterialTheme.shapes.medium,
            color = Color.LightGray, // todo pick a nicer color
        ),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_discover_movies),
            contentDescription = null,
            tint = Purple700,
            modifier = Modifier.align(alignment = Alignment.Center)
        )
    }
}

@Composable
fun LoadingMoviePoster(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.background(
            shape = MaterialTheme.shapes.medium,
            color = Color.LightGray, // todo pick a nicer color
        ),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(24.dp),
            strokeWidth = 2.dp
        )
    }
}