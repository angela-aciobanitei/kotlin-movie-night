package com.ang.acb.movienight.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ang.acb.movienight.domain.entities.MovieDetails
import com.ang.acb.movienight.utils.Constants.POSTER_URL
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.glide.rememberGlidePainter

@Composable
fun MoviePosterInfo(
    movieDetails: MovieDetails,
) {
    Row(
        Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        val painter = rememberGlidePainter(
            request = POSTER_URL + movieDetails.movie.posterPath,
            requestBuilder = {
                val options = RequestOptions()
                options.transform(CenterCrop(), RoundedCorners(12))
                apply(options)
            }
        )

        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .weight(2f)
                .aspectRatio(2 / 3f)
                .clip(MaterialTheme.shapes.medium),
            alignment = Alignment.TopStart,
        )

        // TODO Handle poster image loading/error
//        when (painter.loadState) {
//            is ImageLoadState.Loading ->
//            is ImageLoadState.Error ->
//        }

        MovieInfoGenres(
            movieDetails = movieDetails,
            modifier = Modifier
                .weight(3f)
                .padding(start = 16.dp)
        )
    }
}

@Composable
private fun MovieInfoGenres(
    movieDetails: MovieDetails,
    modifier: Modifier = Modifier,
) {
    Column(modifier.fillMaxWidth()) {
        MovieTitle(title = movieDetails.movie.title ?: "")

        Spacer(modifier = Modifier.height(32.dp))

        FlowRow(
            mainAxisSpacing = 4.dp,
            crossAxisSpacing = 8.dp,
        ) {
            movieDetails.genres.forEach {
                if (it.name != null) GenreChip(genreName = it.name)
            }
        }
    }

}

@Composable
private fun MovieTitle(title: String) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.W700)
        )
    }
}

@Composable
private fun GenreChip(genreName: String) {
    Surface(
        elevation = 4.dp,
        shape = RoundedCornerShape(70),
        color = MaterialTheme.colors.primary
    ) {
        Text(
            text = genreName,
            style = MaterialTheme.typography.body2,
            color = Color.White,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
        )
    }
}