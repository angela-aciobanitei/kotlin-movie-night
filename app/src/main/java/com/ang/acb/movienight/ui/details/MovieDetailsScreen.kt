package com.ang.acb.movienight.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ang.acb.movienight.ui.common.LoadingBox
import com.ang.acb.movienight.ui.common.MessageBox

@Composable
fun MovieDetailsScreen(
    viewModel: MovieDetailsViewModel = hiltViewModel(),
    upPressed: () -> Unit
) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            val title = viewModel.movieDetails?.movie?.title
            MovieDetailsTopBar(
                title = title ?: "",
                upPressed = upPressed
            )
        }
    ) {
        if (viewModel.isLoading) {
            LoadingBox()
        } else {
            if (viewModel.errorMessage != null) {
                MessageBox(messageResId = viewModel.errorMessage!!)
            } else {
                Column(Modifier.verticalScroll(scrollState)) {
                    val modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    val titleStyle = TextStyle(fontWeight = FontWeight.W700, fontSize = 18.sp)
                    Text("${viewModel.movieDetails?.movie?.title}", modifier = modifier, style = titleStyle)
                    Text("Overview", modifier = modifier, style = titleStyle)
                    Text("${viewModel.movieDetails?.movie?.overview}", modifier = modifier)
                    Text("Genres", modifier = modifier, style = titleStyle)
                    Text("${viewModel.movieDetails?.genres?.map { it.name }}", modifier = modifier)
                    Text("Cast", modifier = modifier, style = titleStyle)
                    Text("${viewModel.movieDetails?.cast?.map { it.actorName }}", modifier = modifier)
                    Text("Trailers", modifier = modifier, style = titleStyle)
                    Text("${viewModel.movieDetails?.trailers?.map { it.name }}", modifier = modifier)
                }
            }
        }
    }
}