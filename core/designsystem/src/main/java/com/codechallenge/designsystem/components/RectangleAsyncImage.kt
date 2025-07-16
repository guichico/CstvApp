package com.codechallenge.designsystem.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codechallenge.designsystem.theme.CstvAppTheme

@Composable
fun RectangleAsyncImage(
    modifier: Modifier = Modifier,
    url: String
) {
    CstvAsyncImage(
        modifier = modifier
            .clip(RoundedCornerShape(CstvAppTheme.spacing.small)),
        url = url
    )
}

@Preview
@Composable
private fun RectangleAsyncImagePreview() {
    CstvAppTheme {
        RectangleAsyncImage(
            modifier = Modifier
                .size(60.dp),
            url = ""
        )
    }
}