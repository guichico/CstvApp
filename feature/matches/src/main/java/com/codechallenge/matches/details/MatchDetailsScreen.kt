package com.codechallenge.matches.details

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.codechallenge.designsystem.components.RectangleAsyncImage
import com.codechallenge.designsystem.icon.CstvAppIcons
import com.codechallenge.designsystem.theme.CardColor
import com.codechallenge.designsystem.theme.CstvAppTheme
import com.codechallenge.designsystem.theme.PlayerNameColor
import com.codechallenge.designsystem.views.ErrorView
import com.codechallenge.designsystem.views.LoadingView
import com.codechallenge.matches.TeamVsTeamRow
import com.codechallenge.matches.model.MatchUI
import com.codechallenge.model.Player

const val PLAYER_PROFILE_SIZE = 48

@Composable
fun MatchDetailsScreen(
    matchDetailsViewModel: MatchDetailsViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    val match = matchDetailsViewModel.matchParam
    val playersUIState by matchDetailsViewModel.playersUIState.collectAsState()

    MatchDetailsScreenContent(
        match = match,
        playersUIState = playersUIState,
        onBackClick = onBackClick,
        onRetryClick = { }
    )
}

@Composable
private fun MatchDetailsScreenContent(
    match: MatchUI,
    playersUIState: PlayersUiState,
    onBackClick: () -> Unit,
    onRetryClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Header(
            title = match.leagueAndSerieLabel,
            onBackClick = onBackClick
        )
        Spacer(modifier = Modifier.height(CstvAppTheme.spacing.extraExtraLarge))
        MatchInfo(
            match = match
        )
        Spacer(modifier = Modifier.height(CstvAppTheme.spacing.extraLarge))

        AnimatedContent(
            playersUIState
        ) { playersUIState ->
            when (playersUIState) {
                is PlayersUiState.Loading -> {
                    LoadingView()
                }

                is PlayersUiState.Success -> {
                    PlayersView(
                        teamAPlayers = playersUIState.teamAPlayers,
                        teamBPlayers = playersUIState.teamBPlayers
                    )
                }

                is PlayersUiState.Error -> {
                    ErrorView(
                        modifier = Modifier
                            .padding(horizontal = CstvAppTheme.spacing.extraExtraLarge),
                        onRetryClick = onRetryClick
                    )
                }
            }
        }
    }
}

@Composable
private fun Header(
    title: String?,
    onBackClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        IconButton(onClick = { onBackClick() }) {
            Icon(
                imageVector = CstvAppIcons.BackBtn,
                contentDescription = null,
            )
        }
        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 52.dp),
            text = title ?: "",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = CstvAppTheme.typography.robotoTitleMedium
        )
    }
}

@Composable
private fun ColumnScope.MatchInfo(
    match: MatchUI
) {
    TeamVsTeamRow(
        homeTeam = match.teamA,
        visitingTeam = match.teamB,
    )
    Spacer(modifier = Modifier.height(CstvAppTheme.spacing.extraLarge))
    Text(
        modifier = Modifier
            .align(Alignment.CenterHorizontally),
        text = match.dateFormatted ?: "",
        style = CstvAppTheme.typography.robotoBold2
    )
}

@Composable
private fun PlayersView(
    teamAPlayers: List<Player>,
    teamBPlayers: List<Player>
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        PlayersList(
            modifier = Modifier
                .weight(0.5f),
            players = teamAPlayers,
            isLtr = false
        )
        Spacer(modifier = Modifier.width(CstvAppTheme.spacing.medium))
        PlayersList(
            modifier = Modifier
                .weight(0.5f),
            players = teamBPlayers,
            isLtr = true
        )
    }
}

@Composable
private fun PlayersList(
    modifier: Modifier = Modifier,
    players: List<Player>,
    isLtr: Boolean
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        players.forEach { player ->
            when {
                isLtr -> {
                    RightPlayerCard(
                        player = player
                    )
                }

                else -> {
                    LeftPlayerCard(
                        player = player
                    )
                }
            }
            Spacer(modifier = Modifier.height(CstvAppTheme.spacing.medium))
        }
    }
}

@Composable
private fun PlayerProfileImage(
    playerProfileUrl: String?
) {
    Row(
        modifier = Modifier
            .offset(y = -CstvAppTheme.spacing.extraSmall),
    ) {
        Spacer(modifier = Modifier.width(CstvAppTheme.spacing.medium))
        RectangleAsyncImage(
            modifier = Modifier
                .size(PLAYER_PROFILE_SIZE.dp),
            contentScale = ContentScale.Crop,
            url = playerProfileUrl
        )
        Spacer(modifier = Modifier.width(CstvAppTheme.spacing.medium))
    }
}

@Composable
private fun RowScope.PlayerInfoColumn(
    modifier: Modifier = Modifier,
    nickName: String?,
    name: String?,
    isLtr: Boolean
) {
    Column(
        modifier = modifier
            .align(Alignment.Bottom)
            .offset(y = -CstvAppTheme.spacing.extraSmall),
        horizontalAlignment = if (isLtr) Alignment.Start else Alignment.End
    ) {
        Text(
            modifier = Modifier,
            text = nickName ?: "",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = CstvAppTheme.typography.robotoBold1
        )
        Text(
            modifier = Modifier,
            text = name ?: "",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = CstvAppTheme.typography.robotoRegular3,
            color = PlayerNameColor
        )
        Spacer(modifier = Modifier.height(CstvAppTheme.spacing.small))
    }
}

@Composable
private fun LeftPlayerCard(
    player: Player,
) {
    PlayerCard(
        cardShape = RoundedCornerShape(
            topEnd = CstvAppTheme.spacing.medium,
            bottomEnd = CstvAppTheme.spacing.medium
        )
    ) {
        PlayerInfoColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            nickName = player.nickName,
            name = player.name,
            isLtr = false
        )

        PlayerProfileImage(
            playerProfileUrl = player.pictureUrl
        )
    }
}

@Composable
private fun RightPlayerCard(
    player: Player,
) {
    PlayerCard(
        cardShape = RoundedCornerShape(
            topStart = CstvAppTheme.spacing.medium,
            bottomStart = CstvAppTheme.spacing.medium
        )
    ) {
        PlayerProfileImage(
            playerProfileUrl = player.pictureUrl
        )
        PlayerInfoColumn(
            nickName = player.nickName,
            name = player.name,
            isLtr = true
        )
    }
}

@Composable
private fun PlayerCard(
    cardShape: Shape,
    content: @Composable RowScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = CstvAppTheme.spacing.extraSmall)
    ) {
        val playerInfoRowHeight = (PLAYER_PROFILE_SIZE + CstvAppTheme.spacing.small.value).dp

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(playerInfoRowHeight)
                .background(
                    color = CardColor,
                    shape = cardShape
                )
        ) {
            content()
        }
    }
}

@Preview
@Composable
private fun MatchDetailsScreenPreview(
    @PreviewParameter(MatchDetailsScreenPreviewProvider::class) matchDetailsScreenData: MatchDetailsScreenData
) {
    CstvAppTheme {
        MatchDetailsScreenContent(
            match = matchDetailsScreenData.match,
            playersUIState = matchDetailsScreenData.playersUiState,
            onBackClick = {},
            onRetryClick = {}
        )
    }
}