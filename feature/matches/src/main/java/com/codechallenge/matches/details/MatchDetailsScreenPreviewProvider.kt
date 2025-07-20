package com.codechallenge.matches.details

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.codechallenge.matches.model.MatchUI
import com.codechallenge.model.League
import com.codechallenge.model.Player
import com.codechallenge.model.Serie
import com.codechallenge.model.Team
import java.time.LocalDateTime

private val fakeMatch = MatchUI(
    id = 0,
    teamA = Team(id = 0, name = "Team 1", imgUrl = "https://cdn.pandascore.co/images/team/image/3213/220px_team_liquidlogo_square.png"),
    teamB = Team(id = 0, name = "Team 2", imgUrl = "https://cdn.pandascore.co/images/team/image/3240/208px_mouz_2021_allmode.png"),
    date = LocalDateTime.now(),
    league = League(
        id = 0,
        name = "League",
        imgUrl = ""
    ),
    serie = Serie(
        id = 0,
        name = "serie"
    ),
    leagueAndSerieLabel = "League - Serie",
)

private val fakePlayers = List(10) {
    Player(
        id = 0,
        name = "Nome Jogador",
        nickName = "Nickname",
        pictureUrl = "https://cdn.pandascore.co/images/player/image/17527/nitr0_iem_sydney_2019.png"
    )
}

data class MatchDetailsScreenData(
    val match: MatchUI,
    val playersUiState: PlayersUiState
)

class MatchDetailsScreenPreviewProvider : PreviewParameterProvider<MatchDetailsScreenData> {
    override val values = sequenceOf(
        MatchDetailsScreenData(
            match = fakeMatch,
            playersUiState = PlayersUiState.Loading
        ),
        MatchDetailsScreenData(
            match = fakeMatch,
            playersUiState = PlayersUiState.Success(teamAPlayers = fakePlayers, teamBPlayers = fakePlayers)
        ),
        MatchDetailsScreenData(
            match = fakeMatch,
            playersUiState = PlayersUiState.Error(exception = Throwable())
        ),
    )
}