package com.ang.acb.movienight.ui.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun <T> Carousel(
    items: List<T>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    itemSpacing: Dp = 0.dp,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    itemContent: @Composable LazyItemScope.(T, PaddingValues) -> Unit
) {
    val halfSpacing = itemSpacing / 2
    val spacingContent = PaddingValues(halfSpacing, 0.dp, halfSpacing, 0.dp)
    val layoutDir = LocalLayoutDirection.current

    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(
            start = (contentPadding.calculateStartPadding(layoutDir) - halfSpacing).coerceAtLeast(0.dp),
            top = contentPadding.calculateTopPadding(),
            end = (contentPadding.calculateEndPadding(layoutDir) - halfSpacing).coerceAtLeast(0.dp),
            bottom = contentPadding.calculateBottomPadding(),
        ),
        verticalAlignment = verticalAlignment
    ) {
        items(items) { item ->
            itemContent(item, spacingContent)
        }
    }
}