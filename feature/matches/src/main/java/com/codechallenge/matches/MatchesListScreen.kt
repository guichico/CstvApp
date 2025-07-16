package com.codechallenge.matches

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MatchesListScreen(
    onMatchClick: () -> Unit
) {
    MatchesListScreenContent(
        onMatchClick = onMatchClick
    )
}

@Composable
fun MatchesListScreenContent(
    onMatchClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Partidas",
            style = MaterialTheme.typography.titleLarge
        )
        // TODO Remove it, for now it's just to test navigation
        Button(
            onClick = onMatchClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray
            )
        ) {
            Text(
                text = "navigate to next screen"
            )
        }
    }
}

@Preview
@Composable
fun MatchesListScreenPreview(

) {
    MatchesListScreenContent(
        onMatchClick = { }
    )
}