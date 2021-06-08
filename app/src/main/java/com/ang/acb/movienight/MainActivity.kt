package com.ang.acb.movienight

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ang.acb.movienight.movielist.MoviesScreen
import com.ang.acb.movienight.ui.theme.MovieNightTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieNightTheme {
                MoviesScreen()
            }
        }
    }
}