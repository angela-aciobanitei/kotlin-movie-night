package com.ang.acb.movienight.ui.filter

import androidx.compose.foundation.layout.Box
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import com.ang.acb.movienight.data.source.local.asStringResId
import com.ang.acb.movienight.domain.entities.MovieFilter

@Composable
fun FilterMoviesTopBar(
    filterLabel: Int,
    onFilterChanged: (filter: MovieFilter) -> Unit
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.surface.copy(alpha = 0.95f),
        contentColor = MaterialTheme.colors.onSurface,
        title = { Text(text = stringResource(filterLabel)) },
        actions = { FilterMoviesMenu(onFilterChanged) }
    )
}

@Composable
fun FilterMoviesMenu(
    onFilterChanged: (filter: MovieFilter) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        IconButton(onClick = { expanded = true }) {
            Icon(
                imageVector = Icons.Default.Sort,
                contentDescription = null,
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            FilterMenuItem(
                filter = MovieFilter.POPULAR,
                onClick = {
                    onFilterChanged(MovieFilter.POPULAR)
                    expanded = false
                },
            )

            FilterMenuItem(
                filter = MovieFilter.TOP_RATED,
                onClick = {
                    onFilterChanged(MovieFilter.TOP_RATED)
                    expanded = false
                },
            )

            FilterMenuItem(
                filter = MovieFilter.NOW_PLAYING,
                onClick = {
                    onFilterChanged(MovieFilter.NOW_PLAYING)
                    expanded = false
                },
            )

            FilterMenuItem(
                filter = MovieFilter.UPCOMING,
                onClick = {
                    onFilterChanged(MovieFilter.UPCOMING)
                    expanded = false
                },
            )
        }
    }
}

@Composable
fun FilterMenuItem(
    filter: MovieFilter,
    onClick: () -> Unit,
) {
    DropdownMenuItem(onClick = onClick) {
        Text(text = stringResource(id = filter.asStringResId()))
    }
}