package com.codechallenge.matches.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MatchDetailsScreen(
    onBackClick: () -> Unit,
) {
    MatchDetailsScreenContent(
        onBackClick = onBackClick
    )
}

@Composable
fun MatchDetailsScreenContent(
    onBackClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        IconButton(onClick = { onBackClick() }) {
            Icon(
                // TODO Move it to design system when created
                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                contentDescription = null,
            )
        }
        Text(
            text = "MatchDetailsScreen"
        )
    }
}

@Preview
@Composable
fun MatchDetailsScreenPreview(

) {
    MatchDetailsScreenContent(
        onBackClick = {}
    )
}