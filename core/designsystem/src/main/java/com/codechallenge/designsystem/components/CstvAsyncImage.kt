package com.codechallenge.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
fun CstvAsyncImage(
    modifier: Modifier = Modifier,
    url: String
) {
    var isLoading by remember { mutableStateOf(true) }
    var isError by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        AsyncImage(
            modifier = Modifier,
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .crossfade(true)
                .build(),
            alignment = Alignment.TopCenter,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            onLoading = {
                isLoading = true
                isError = false
            },
            onSuccess = {
                isLoading = false
                isError = false
            },
            onError = {
                isLoading = false
                isError = true
            },
        )

        if (isLoading || isError) {
            Box(
                modifier = modifier
                    .clip(CircleShape)
                    .background(ImagePlaceholderColor)
            )
        }
    }
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