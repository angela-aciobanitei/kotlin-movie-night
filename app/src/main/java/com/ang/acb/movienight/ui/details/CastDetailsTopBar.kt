package com.ang.acb.movienight.ui.details

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.ang.acb.movienight.R

@Composable
fun CastDetailsTopBar(
    upPressed: () -> Unit
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.surface.copy(alpha = 0.95f),
        contentColor = MaterialTheme.colors.onSurface,
        title = {
            Text(
                modifier = Modifier.padding(end = 32.dp),
                text = stringResource(R.string.cast_details_topbar_label),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = upPressed) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.topbar_up_button_content_description),
                )
            }
        }
    )
}