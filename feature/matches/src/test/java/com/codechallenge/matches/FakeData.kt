package com.codechallenge.matches

import com.codechallenge.model.League
import com.codechallenge.model.Match
import com.codechallenge.model.Player
import com.codechallenge.model.Serie
import com.codechallenge.model.Team
import java.time.LocalDateTime

internal val fakePlayers1 = List(5) {
    Player(
        id = 1,
        name = "Nome Jogador 1",
        nickName = "Nickname 2",
        pictureUrl = "https://cdn.pandascore.co/images/player/image/17527/nitr0_iem_sydney_2019.png"
    )
}
internal val fakePlayers2 = List(5) {
    Player(
        id = 2,
        name = "Nome Jogador 2",
        nickName = "Nickname 2",
        pictureUrl = "https://cdn.pandascore.co/images/player/image/17527/nitr0_iem_sydney_2019.png"
    )
}

internal val fakeMatch =
    Match(
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
        )
    )