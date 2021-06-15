package com.ang.acb.movienight.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ang.acb.movienight.R
import com.ang.acb.movienight.ui.theme.Purple700
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.accompanist.glide.rememberGlidePainter
import com.google.accompanist.imageloading.ImageLoadState

private const val posterWidth = 50
private const val posterHeight = 75
private const val roundingRadius = 12

@Composable
fun MovieAvatar(
    avatarUrl: String,
) {
    Box(
        modifier = Modifier
            .width(posterWidth.dp)
            .height(posterHeight.dp)
            .clip(shape = RoundedCornerShape(roundingRadius)),
        contentAlignment = Alignment.Center,
    ) {
        val painter = rememberGlidePainter(
            request = avatarUrl,
            requestBuilder = {
                val options = RequestOptions()
                options.transform(CenterCrop(), RoundedCorners(roundingRadius))
                apply(options)
            }
        )

        Image(painter = painter, contentDescription = null)

        when (painter.loadState) {
            is ImageLoadState.Loading -> LoadingMovieAvatar()
            is ImageLoadState.Error -> DefaultMovieAvatar()
        }
    }
}

@Composable
fun DefaultMovieAvatar() {
    Box(
        modifier = Modifier
            .width(posterWidth.dp)
            .height(posterHeight.dp)
            .background(shape = RoundedCornerShape(roundingRadius), color = Color.LightGray),
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
fun LoadingMovieAvatar() {
    Box(
        modifier = Modifier
            .width(posterWidth.dp)
            .height(posterHeight.dp)
            .background(shape = RoundedCornerShape(roundingRadius), color = Color.LightGray),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(24.dp),
            strokeWidth = 2.dp
        )
    }
}