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
import com.ang.acb.movienight.ui.theme.midnight200
import com.ang.acb.movienight.ui.theme.midnight800

@ExperimentalAnimationApi
@Composable
fun SearchMoviesTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = midnight200,
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
                        tint = midnight200,
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
            textColor = midnight800,
            disabledTextColor = midnight200,
            placeholderColor = midnight200,
            disabledPlaceholderColor = midnight200,
            backgroundColor = Color.White,
            cursorColor = midnight800,
            errorCursorColor = MaterialTheme.colors.error,
            focusedIndicatorColor = midnight200,
            unfocusedIndicatorColor = midnight200,
            errorIndicatorColor = MaterialTheme.colors.error,
        )
    )
}