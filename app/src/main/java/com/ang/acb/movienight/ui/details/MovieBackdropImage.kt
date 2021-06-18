package com.ang.acb.movienight.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.google.accompanist.glide.rememberGlidePainter
import com.google.accompanist.imageloading.ImageLoadState

@Composable
fun MovieBackdropImage(
    backdropUrl: String,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16f / 10),
        contentAlignment = Alignment.Center,
    ) {
        val painter = rememberGlidePainter(
            request = backdropUrl,
            requestBuilder = {
                val options = RequestOptions()
                options.transform(CenterCrop())
                apply(options)
            }
        )

        Image(painter = painter, contentDescription = null)

        when (painter.loadState) {
            is ImageLoadState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16f / 10),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator()
                }
            }
            is ImageLoadState.Error -> Box(modifier = Modifier.fillMaxWidth())
        }
    }
}