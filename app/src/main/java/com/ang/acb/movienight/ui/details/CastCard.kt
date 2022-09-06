package com.ang.acb.movienight.ui.details

import androidx.compose.foundation.clickable
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ang.acb.movienight.domain.entities.Cast

@Composable
fun CastCard(
    cast: Cast,
    modifier: Modifier = Modifier,
    onItemClick: (cast: Cast) -> Unit,
) {
    Card(modifier = modifier) {
        CastProfileImage(
            profileImageUrl = cast.profileImageUrl,
            modifier = Modifier.clickable { onItemClick(cast) },
        )
    }
}



