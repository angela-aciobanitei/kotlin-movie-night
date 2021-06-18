package com.ang.acb.movienight.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.ang.acb.movienight.domain.entities.Cast
import com.ang.acb.movienight.ui.theme.Purple700
import com.ang.acb.movienight.utils.Constants.CAST_AVATAR_URL
import com.google.accompanist.glide.rememberGlidePainter
import com.google.accompanist.imageloading.ImageLoadState

@Composable
fun CastCard(
    cast: Cast,
    modifier: Modifier = Modifier,
    onItemClick: (movieId: Long, castId: Long) -> Unit,
) {
    Card(modifier = modifier) {
        Box(modifier = Modifier.clickable { onItemClick(cast.movieId, cast.id) }) {
            val castAvatarUrl = CAST_AVATAR_URL + cast.profileImagePath
            val painter = rememberGlidePainter(request = castAvatarUrl, fadeIn = true)

            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .matchParentSize()
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop,
            )

            when (painter.loadState) {
                is ImageLoadState.Loading -> LoadingCastAvatar(modifier)
                is ImageLoadState.Error -> DefaultCastAvatar(modifier)
            }
        }
    }
}

@Composable
fun DefaultCastAvatar(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.clip(MaterialTheme.shapes.medium),
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
fun LoadingCastAvatar(
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

