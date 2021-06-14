package com.ang.acb.movienight.ui.filtermovies

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import com.ang.acb.movienight.R
import com.ang.acb.movienight.ui.theme.DarkGray
import com.ang.acb.movienight.ui.theme.LightGray
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.accompanist.glide.rememberGlidePainter
import com.google.accompanist.imageloading.ImageLoadState

@Composable
fun MovieAvatar(
    avatarUrl: String,
    roundingRadius: Int = 12,
) {
    Box(
        modifier = Modifier.clip(shape = RoundedCornerShape(roundingRadius)),
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
            is ImageLoadState.Loading -> CircularProgressIndicator(Modifier.align(Alignment.Center))
            is ImageLoadState.Error -> DefaultMovieAvatar()
        }
    }
}

@Composable
fun DefaultMovieAvatar(
    roundingRadius: Int = 12,
) {
    Box(
        modifier = Modifier.background(
            shape = RoundedCornerShape(roundingRadius),
            color = LightGray
        ),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_movie_24),
            contentDescription = null,
            tint = DarkGray,
            modifier = Modifier
                .scale(2f)
                .align(alignment = Alignment.Center)
        )
    }
}