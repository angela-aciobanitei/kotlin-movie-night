package com.ang.acb.movienight.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ang.acb.movienight.R

@Composable
fun PagingErrorItem(
    message: String,
    modifier: Modifier = Modifier,
    onRetryClick: () -> Unit
) {
    Row(
        modifier = modifier.padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = message,
            maxLines = 1,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.subtitle2,
            color = MaterialTheme.colors.error,
        )
        Button(onClick = onRetryClick) {
            Text(text = stringResource(R.string.try_again_button_label))
        }
    }
}