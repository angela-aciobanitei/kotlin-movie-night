package com.ang.acb.movienight.ui.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.ang.acb.movienight.R
import com.ang.acb.movienight.ui.theme.DarkerGray
import com.ang.acb.movienight.ui.theme.LighterGray

@ExperimentalAnimationApi
@Composable
fun SearchMoviesTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = LighterGray,
            )
        },
        trailingIcon = {
            AnimatedVisibility(
                visible = value.text.isNotEmpty(),
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                IconButton(
                    onClick = { onValueChange(TextFieldValue()) },
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = stringResource(R.string.clear_txt_icon_cd),
                        tint = LighterGray,
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
            keyboardType = KeyboardType.Text,
        ),
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(text = stringResource(id = R.string.search_movies_hint))
        },
        maxLines = 1,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            textColor = DarkerGray,
            disabledTextColor = LighterGray,
            placeholderColor = LighterGray,
            disabledPlaceholderColor = LighterGray,
            backgroundColor = Color.White,
            cursorColor = DarkerGray,
            errorCursorColor = MaterialTheme.colors.error,
            focusedIndicatorColor = LighterGray,
            unfocusedIndicatorColor = LighterGray,
            errorIndicatorColor = MaterialTheme.colors.error,
        )
    )
}