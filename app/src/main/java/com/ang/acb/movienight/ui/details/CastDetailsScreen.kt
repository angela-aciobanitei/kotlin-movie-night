package com.ang.acb.movienight.ui.details

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ang.acb.movienight.R
import com.ang.acb.movienight.ui.common.LoadingBox
import com.ang.acb.movienight.ui.common.MessageBox
import timber.log.Timber

@Composable
fun CastDetailsScreen(
    viewModel: CastDetailsViewModel = hiltViewModel(),
    upPressed: () -> Unit
) {
    val context = LocalContext.current
    Scaffold(
        topBar = { CastDetailsTopBar(upPressed = upPressed) }
    ) {
        if (viewModel.isLoading) {
            LoadingBox()
        } else {
            if (viewModel.errorMessage != null) {
                MessageBox(messageResId = viewModel.errorMessage!!)
            } else {
                viewModel.castDetails?.let {
                    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                        CastInfoAvatarRow(
                            cast = it,
                            openImdb = { imdbUrl ->
                                openImdbPage(imdbUrl, context)
                            }
                        )

                        if (it.biography.isNullOrEmpty().not()) {
                            MovieInfoHeader(stringResource(R.string.cast_details_biography_label))
                            Text(it.biography!!, modifier = Modifier.padding(horizontal = 16.dp))
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }
        }
    }
}

private fun openImdbPage(imdbUrl: String, context: Context) {
    val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(imdbUrl))
    try {
        context.startActivity(webIntent)
    } catch (e: ActivityNotFoundException) {
        Timber.e(e)
    }
}