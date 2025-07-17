package com.codechallenge.matches.details

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.codechallenge.model.League
import com.codechallenge.model.Match
import com.codechallenge.model.Player
import com.codechallenge.model.Serie
import com.codechallenge.model.Team

private val fakeMatch = Match(
    id = 0,
    teams = listOf(
        Team(id = 0, name = "Team 1", logoUrl = "https://cdn.pandascore.co/images/team/image/3213/220px_team_liquidlogo_square.png"),
        Team(id = 0, name = "Team 2", logoUrl = "https://cdn.pandascore.co/images/team/image/3240/208px_mouz_2021_allmode.png")
    ),
    players = emptyList(),
    date = "Hoje, 21:00",
    league = League(
        id = 0,
        name = "League",
        imgUrl = ""
    ),
    serie = Serie(
        id = 0,
        name = "serie"
    )
)

private val fakePlayers = List(10) {
    Player(
        id = 0,
        name = "Nome Jogador",
        nickName = "Nickname",
        pictureUrl = "https://cdn.pandascore.co/images/player/image/17527/nitr0_iem_sydney_2019.png"
    )
}

class MatchDetailsScreenPreviewProvider : PreviewParameterProvider<MatchUiState> {
    override val values = sequenceOf(
        MatchUiState.Loading(fakeMatch),
        MatchUiState.Success(fakeMatch.copy(players = fakePlayers)),
        MatchUiState.Error(exception = Throwable())
    )
}