package com.ang.acb.movienight.ui.details

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.ang.acb.movienight.R

@Composable
fun MovieDetailsTopBar(
    title: String,
    isFavorite: Boolean,
    isFavoriteLoading: Boolean,
    onFavoriteClicked: () -> Unit,
    upPressed: () -> Unit
) {
    TopAppBar(
        backgroundColor = Color.White,
        title = {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = upPressed) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_back),
                    contentDescription = stringResource(R.string.topbar_up_button_content_description),
                )
            }
        },
        actions = {
            if (isFavoriteLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(24.dp),
                    color = MaterialTheme.colors.primary,
                    strokeWidth = 2.dp
                )
            } else {
                IconButton(
                    onClick = onFavoriteClicked,
                    content = {
                        Icon(
                            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = null,
                            tint = MaterialTheme.colors.primary
                        )
                    }
                )
            }
        }
    )
}