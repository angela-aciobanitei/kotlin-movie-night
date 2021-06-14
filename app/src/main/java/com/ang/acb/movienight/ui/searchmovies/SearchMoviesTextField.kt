package com.ang.acb.movienight.ui.searchmovies

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.ang.acb.movienight.R
import com.ang.acb.movienight.ui.theme.DarkerGray
import com.ang.acb.movienight.ui.theme.LightRed
import com.ang.acb.movienight.ui.theme.LighterGray

@ExperimentalComposeUiApi
@Composable
fun SearchMoviesTextField(
    textState: MutableState<TextFieldValue>,
    onValueChange: (String) -> Unit,
    search: () -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_search_24),
                contentDescription = null,
                tint = LighterGray,
            )
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
            keyboardType = KeyboardType.Text,
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                keyboardController?.hide()
                search()
            }
        ),
        value = textState.value,
        onValueChange = {
            textState.value = it
            onValueChange(it.text)
        },
        placeholder = {
            Text(text = stringResource(id = R.string.search_movies_hint))
        },
        colors = TextFieldDefaults.textFieldColors(
            textColor = DarkerGray,
            disabledTextColor = LighterGray,
            placeholderColor = LighterGray,
            disabledPlaceholderColor = LighterGray,
            backgroundColor = Color.White,
            cursorColor = DarkerGray,
            errorCursorColor = LightRed,
            focusedIndicatorColor = LighterGray,
            unfocusedIndicatorColor = LighterGray,
            errorIndicatorColor = LightRed,
        )
    )
}