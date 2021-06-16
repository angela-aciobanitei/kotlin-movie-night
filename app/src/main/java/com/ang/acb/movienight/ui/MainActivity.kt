package com.ang.acb.movienight.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.ExperimentalComposeUiApi
import com.ang.acb.movienight.ui.main.MovieNightScreen
import com.ang.acb.movienight.ui.theme.MovieNightTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @FlowPreview
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieNightTheme {
                MovieNightScreen()
            }
        }
    }
}