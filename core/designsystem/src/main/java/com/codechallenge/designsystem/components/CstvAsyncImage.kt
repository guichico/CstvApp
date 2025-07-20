package com.codechallenge.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.codechallenge.designsystem.theme.ImagePlaceholderColor

@Composable
internal fun CstvAsyncImage(
    modifier: Modifier = Modifier,
    shape: Shape,
    contentScale: ContentScale = ContentScale.Fit,
    url: String?
) {
    var isLoading by remember { mutableStateOf(true) }
    var isError by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        AsyncImage(
            modifier = modifier,
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .build(),
            alignment = Alignment.Center,
            contentDescription = null,
            contentScale = contentScale,
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
                    .clip(shape)
                    .background(ImagePlaceholderColor)
            )
        }
    }
}
