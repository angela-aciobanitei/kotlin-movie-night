package com.ang.acb.movienight.ui.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.ang.acb.movienight.R

@Composable
fun SearchResultEmptyMessage(
    searchTerm: String,
) {
    Text(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
        textAlign = TextAlign.Center,
        text = buildAnnotatedString {
            append(stringResource(R.string.search_no_results_start))
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colors.primary
                )
            ) {
                append(" $searchTerm")
            }
            append(". ")
            append(stringResource(R.string.search_no_results_end))
        }
    )
}