package com.ang.acb.movienight.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.ang.acb.movienight.ui.theme.midnight50
import com.google.accompanist.glide.rememberGlidePainter
import com.google.accompanist.imageloading.ImageLoadState

@Composable
fun CastProfileImage(
    profileImageUrl: String?,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        if (profileImageUrl == null) {
            PlaceholderProfileImage(modifier.matchParentSize())
        } else {
            val painter = rememberGlidePainter(request = profileImageUrl, fadeIn = true)

            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .matchParentSize()
                    .clip(MaterialTheme.shapes.medium),
            )

            when (painter.loadState) {
                is ImageLoadState.Loading -> LoadingProfileImage(modifier.matchParentSize())
                is ImageLoadState.Error -> PlaceholderProfileImage(modifier.matchParentSize())
            }
        }
    }
}

@Composable
private fun PlaceholderProfileImage(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.background(
            shape = MaterialTheme.shapes.medium,
            color = midnight50,
        ),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = null,
            tint = MaterialTheme.colors.primary,
            modifier = Modifier.scale(2f)
        )
    }
}

@Composable
private fun LoadingProfileImage(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.clip(MaterialTheme.shapes.medium),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.Center),
            strokeWidth = 2.dp
        )
    }
}