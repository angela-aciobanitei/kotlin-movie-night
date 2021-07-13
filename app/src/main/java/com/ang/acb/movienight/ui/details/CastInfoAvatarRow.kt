package com.ang.acb.movienight.ui.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ang.acb.movienight.R
import com.ang.acb.movienight.domain.entities.CastDetails

@Composable
fun CastInfoAvatarRow(
    cast: CastDetails,
    openImdb: (imdbUrl: String) -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        CastProfileImage(
            profileImageUrl = cast.profileImageUrl,
            modifier = Modifier
                .weight(2f)
                .aspectRatio(2 / 3f)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(3f)
        ) {
            if (cast.name.isNullOrEmpty().not()) {
                MovieInfoHeader(title = cast.name!!)
            }

            if (cast.birthday.isNullOrEmpty().not()) {
                Text(
                    text = stringResource(
                        id = R.string.cast_details_birthday,
                        formatArgs = arrayOf(cast.birthday!!)
                    ),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }

            if (cast.placeOfBirth.isNullOrEmpty().not()) {
                Text(
                    text = stringResource(
                        id = R.string.cast_details_birthplace,
                        formatArgs = arrayOf(cast.placeOfBirth!!)
                    ),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }

            if (cast.imdbUrl.isNullOrEmpty().not()) {
                Text(
                    text = stringResource(R.string.cast_details_open_imdb_page),
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier
                        .clickable { openImdb(cast.imdbUrl!!) }
                        .padding(16.dp)
                )
            }
        }
    }
}