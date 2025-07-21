package com.codechallenge.designsystem.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.codechallenge.designsystem.R
import com.codechallenge.designsystem.theme.CstvAppTheme

@Composable
fun ErrorView(
    modifier: Modifier = Modifier,
    onRetryClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.error_message),
            style = CstvAppTheme.typography.robotoTitleMedium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(CstvAppTheme.spacing.extraExtraLarge))
        Button(
            onClick = onRetryClick
        ) {
            Text(
                text = stringResource(R.string.retry_btn),
                style = CstvAppTheme.typography.robotoBold1
            )
        }
    }
}

@Preview
@Composable
private fun ErrorViewPreview() {
    CstvAppTheme {
        ErrorView(
            onRetryClick = {}
        )
    }
}