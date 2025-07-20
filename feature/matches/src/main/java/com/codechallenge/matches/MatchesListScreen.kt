package com.codechallenge.matches

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.codechallenge.designsystem.components.CstvAsyncImage
import com.codechallenge.designsystem.theme.CardColor
import com.codechallenge.designsystem.theme.CstvAppTheme
import com.codechallenge.designsystem.theme.MatchTimeColor
import com.codechallenge.designsystem.views.ErrorView
import com.codechallenge.designsystem.views.LoadingView
import com.codechallenge.model.Match

@Composable
fun MatchesListScreen(
    matchesListViewModel: MatchesListViewModel = hiltViewModel(),
    onMatchClick: (Match) -> Unit
) {
    val matchesUIState = matchesListViewModel.matchesUIState.collectAsState()

    MatchesListScreenContent(
        matchesUIState = matchesUIState,
        onMatchClick = onMatchClick,
        onRetryClick = {}
    )
}

@Composable
fun MatchesListScreenContent(
    matchesUIState: State<MatchesUiState>,
    onMatchClick: (Match) -> Unit,
    onRetryClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = CstvAppTheme.spacing.extraExtraLarge,
                top = CstvAppTheme.spacing.extraExtraLarge,
                end = CstvAppTheme.spacing.extraExtraLarge
            )
    ) {
        Text(
            text = stringResource(R.string.matches_title),
            style = CstvAppTheme.typography.robotoTitleLarge
        )
        MatchesUIStateContent(
            matchesUIState = matchesUIState,
            onMatchClick = onMatchClick,
            onRetryClick = onRetryClick
        )
    }
}

@Composable
private fun MatchesUIStateContent(
    matchesUIState: State<MatchesUiState>,
    onMatchClick: (Match) -> Unit,
    onRetryClick: () -> Unit
) {
    when (val matchesUIState = matchesUIState.value) {
        is MatchesUiState.Loading -> {
            LoadingView()
        }

        is MatchesUiState.Success -> {
            Spacer(modifier = Modifier.height(CstvAppTheme.spacing.extraExtraLarge))
            MatchesList(
                matches = matchesUIState.matches,
                onMatchClick = onMatchClick
            )
        }

        is MatchesUiState.Error -> {
            ErrorView(
                onRetryClick = onRetryClick
            )
        }
    }
}

@Composable
private fun MatchesList(
    matches: List<Match>,
    onMatchClick: (Match) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(matches) { match ->
            MatchCard(
                match = match,
                onMatchClick = onMatchClick
            )
            Spacer(modifier = Modifier.height(CstvAppTheme.spacing.extraExtraLarge))
        }
    }
}

@Composable
private fun MatchCard(
    match: Match,
    onMatchClick: (Match) -> Unit
) {
    val shape = RoundedCornerShape(CstvAppTheme.spacing.large)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .background(
                color = CardColor,
                shape = shape
            )
            .clickable(onClick = { onMatchClick(match) })
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            MatchTime(
                date = match.date
            )

            TeamVsTeamRow(
                modifier = Modifier
                    .padding(vertical = CstvAppTheme.spacing.extraLarge),
                homeTeam = match.teams.first(),
                visitingTeam = match.teams[1]
            )

            HorizontalDivider(thickness = 1.dp, color = MatchTimeColor)

            MatchLeague(
                title = "${match.league.name}, ${match.serie.name}",
                leagueImgUrl = match.league.imgUrl
            )
        }
    }
}

@Composable
private fun ColumnScope.MatchTime(
    date: String
) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .background(
                color = MatchTimeColor,
                shape = RoundedCornerShape(
                    topEnd = CstvAppTheme.spacing.large,
                    bottomStart = CstvAppTheme.spacing.large
                )
            )
            .align(Alignment.End)
    ) {
        Text(
            modifier = Modifier
                .padding(CstvAppTheme.spacing.small),
            text = date,
            style = CstvAppTheme.typography.robotoBold3
        )
    }
}

@Composable
private fun MatchLeague(
    title: String,
    leagueImgUrl: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = CstvAppTheme.spacing.small,
                horizontal = CstvAppTheme.spacing.large
            )
    ) {
        CstvAsyncImage(
            modifier = Modifier
                .size(16.dp),
            url = leagueImgUrl
        )
        Spacer(modifier = Modifier.width(CstvAppTheme.spacing.small))
        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically),
            text = title,
            style = CstvAppTheme.typography.robotoRegular1
        )
    }
}

@Preview
@Composable
private fun MatchesListScreenPreview(
    @PreviewParameter(MatchesListScreenPreviewProvider::class) matchesUIState: MatchesUiState
) {
    CstvAppTheme {
        MatchesListScreenContent(
            matchesUIState = remember { mutableStateOf(matchesUIState) },
            onMatchClick = { },
            onRetryClick = {}
        )
    }
}