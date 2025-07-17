package com.codechallenge.matches

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.codechallenge.model.League
import com.codechallenge.model.Match
import com.codechallenge.model.Player
import com.codechallenge.model.Serie
import com.codechallenge.model.Team

private val fakeMatch = Match(
    id = 0,
    teams = listOf(
        Team(id = 0, name = "Team 1", logoUrl = ""),
        Team(id = 0, name = "Team 2", logoUrl = "")
    ),
    players = List(10) {
        Player(
            id = 0,
            name = "Nome Jogador",
            nickName = "Nickname",
            pictureUrl = ""
        )
    },
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

class MatchesListScreenPreviewProvider : PreviewParameterProvider<UiState> {
    override val values = sequenceOf(
        UiState.Loading,
        UiState.Success(List(5) { fakeMatch }),
        UiState.Error(exception = Throwable())
    )
}