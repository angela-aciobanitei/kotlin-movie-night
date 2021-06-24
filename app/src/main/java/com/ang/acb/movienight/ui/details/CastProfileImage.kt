package com.ang.acb.movienight.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.ang.acb.movienight.ui.theme.Purple700
import com.google.accompanist.glide.rememberGlidePainter
import com.google.accompanist.imageloading.ImageLoadState

@Composable
fun CastProfileImage(
    profileImageUrl: String?,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        val painter = rememberGlidePainter(request = profileImageUrl, fadeIn = true)

        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .matchParentSize()
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop,
        )

        when (painter.loadState) {
            is ImageLoadState.Loading -> LoadingCastProfileImager(modifier)
            is ImageLoadState.Error -> DefaultCastProfileImage(modifier)
        }
    }
}

@Composable
private fun DefaultCastProfileImage(
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
            imageVector = Icons.Default.Person,
            contentDescription = null,
            tint = Purple700,
            modifier = Modifier.align(alignment = Alignment.Center)
        )
    }
}

@Composable
private fun LoadingCastProfileImager(
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