package com.ang.acb.movienight.ui.details

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.ang.acb.movienight.R

@Composable
fun CastDetailsTopBar(
    title: String,
    upPressed: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                modifier = Modifier.padding(end = 32.dp),
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
        }
    )
}