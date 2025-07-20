package com.codechallenge.matches

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.codechallenge.matches.model.MatchUI
import com.codechallenge.model.League
import com.codechallenge.model.Serie
import com.codechallenge.model.Team
import java.time.LocalDateTime

private val fakeMatch = MatchUI(
    id = 0,
    teamA = Team(id = 0, name = "Team 1", imgUrl = ""),
    teamB = Team(id = 0, name = "Team 2", imgUrl = ""),
    date = LocalDateTime.now(),
    league = League(
        id = 0,
        name = "League",
        imgUrl = ""
    ),
    serie = Serie(
        id = 0,
        name = "Serie"
    ),
    leagueAndSerieLabel = "League - Serie",
)

class MatchesListScreenPreviewProvider : PreviewParameterProvider<MatchUI> {
    override val values = sequenceOf(fakeMatch)
}