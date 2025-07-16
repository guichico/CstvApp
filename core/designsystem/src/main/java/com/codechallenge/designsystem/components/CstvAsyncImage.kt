package com.codechallenge.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.codechallenge.designsystem.theme.CstvAppTheme
import com.codechallenge.designsystem.theme.ImagePlaceholderColor

@Composable
internal fun CstvAsyncImage(
    modifier: Modifier = Modifier,
    url: String
) {
    AsyncImage(
        modifier = modifier
            .background(ImagePlaceholderColor),
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        alignment = Alignment.TopCenter,
        contentDescription = null,
        contentScale = ContentScale.None
    )
}

@Preview
@Composable
private fun CstvAsyncImagePreview() {
    CstvAppTheme {
        CstvAsyncImage(
            modifier = Modifier
                .size(60.dp),
            url = ""
        )
    }
}