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
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.codechallenge.designsystem.components.CirclePlaceholderAsyncImage
import com.codechallenge.designsystem.theme.CardColor
import com.codechallenge.designsystem.theme.CstvAppTheme
import com.codechallenge.designsystem.theme.MatchTimeColor
import com.codechallenge.designsystem.views.ErrorView
import com.codechallenge.designsystem.views.LoadingView
import com.codechallenge.matches.model.MatchUI
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun MatchesListScreen(
    matchesListViewModel: MatchesListViewModel = hiltViewModel(),
    onMatchClick: (MatchUI) -> Unit
) {
    val matchesPager = matchesListViewModel.matchesPager.collectAsLazyPagingItems()

    MatchesListScreenContent(
        matchesPager = matchesPager,
        onMatchClick = onMatchClick,
        onRetryClick = {}
    )
}

@Composable
private fun MatchesListScreenContent(
    matchesPager: LazyPagingItems<MatchUI>,
    onMatchClick: (MatchUI) -> Unit,
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
            matchesPager = matchesPager,
            onMatchClick = onMatchClick,
            onRetryClick = onRetryClick
        )
    }
}

@Composable
private fun MatchesUIStateContent(
    matchesPager: LazyPagingItems<MatchUI>,
    onMatchClick: (MatchUI) -> Unit,
    onRetryClick: () -> Unit
) {
    when {
        matchesPager.loadState.refresh == LoadState.Loading ||
                matchesPager.loadState.prepend == LoadState.Loading -> {
            LoadingView()
        }

        matchesPager.loadState.refresh is LoadState.Error -> {
            ErrorView(
                onRetryClick = onRetryClick
            )
        }

        else -> {
            Spacer(modifier = Modifier.height(CstvAppTheme.spacing.extraExtraLarge))
            MatchesList(
                matchesPager = matchesPager,
                onMatchClick = onMatchClick
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MatchesList(
    matchesPager: LazyPagingItems<MatchUI>,
    onMatchClick: (MatchUI) -> Unit
) {
    val isRefreshing by remember { mutableStateOf(false) }
    val pullRefreshState = rememberPullToRefreshState()

    PullToRefreshBox(
        modifier = Modifier
            .fillMaxSize(),
        isRefreshing = isRefreshing,
        onRefresh = { matchesPager.refresh() },
        state = pullRefreshState
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(matchesPager.itemCount) { index ->
                matchesPager[index]?.let { match ->
                    MatchCard(
                        match = match,
                        onMatchClick = onMatchClick
                    )
                    Spacer(modifier = Modifier.height(CstvAppTheme.spacing.extraExtraLarge))
                }
            }
            if (matchesPager.loadState.append == LoadState.Loading) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center)
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                        )
                        Spacer(modifier = Modifier.height(CstvAppTheme.spacing.extraExtraLarge))
                    }
                }
            }
        }
    }
}

@Composable
private fun LazyItemScope.MatchCard(
    match: MatchUI,
    onMatchClick: (MatchUI) -> Unit
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
            .animateItem()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            MatchTime(
                backgroundColor = Color(match.dateBgColor.value),
                date = match.dateFormatted ?: ""
            )

            TeamVsTeamRow(
                modifier = Modifier
                    .padding(vertical = CstvAppTheme.spacing.extraLarge),
                homeTeam = match.teamA,
                visitingTeam = match.teamB
            )

            HorizontalDivider(thickness = 1.dp, color = MatchTimeColor)

            MatchLeague(
                title = match.leagueAndSerieLabel,
                leagueImgUrl = match.league?.imgUrl ?: ""
            )
        }
    }
}

@Composable
private fun ColumnScope.MatchTime(
    backgroundColor: Color,
    date: String
) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .background(
                color = backgroundColor,
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
    title: String?,
    leagueImgUrl: String?,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = CstvAppTheme.spacing.small,
                horizontal = CstvAppTheme.spacing.large
            )
    ) {
        CirclePlaceholderAsyncImage(
            modifier = Modifier
                .size(16.dp),
            url = leagueImgUrl
        )
        Spacer(modifier = Modifier.width(CstvAppTheme.spacing.small))
        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically),
            text = title ?: "",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = CstvAppTheme.typography.robotoRegular1
        )
    }
}

@Preview
@Composable
private fun MatchesListScreenPreview(
    @PreviewParameter(MatchesListScreenPreviewProvider::class) fakeMatchUI: MatchUI
) {
    val pagingData = PagingData.from(List(5) { fakeMatchUI })
    val fakeDataFlow = remember { MutableStateFlow(pagingData) }
    val lazyPagingItems = fakeDataFlow.collectAsLazyPagingItems()

    CstvAppTheme {
        MatchesListScreenContent(
            matchesPager = lazyPagingItems,
            onMatchClick = {},
            onRetryClick = {}
        )
    }
}