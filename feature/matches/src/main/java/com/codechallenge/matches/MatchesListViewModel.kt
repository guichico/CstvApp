package com.codechallenge.matches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codechallenge.model.League
import com.codechallenge.model.Match
import com.codechallenge.model.Player
import com.codechallenge.model.Serie
import com.codechallenge.model.Team
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchesListViewModel @Inject constructor(

) : ViewModel() {

    private val fakeMatch = Match(
        id = 0,
        teams = listOf(
            Team(id = 0, name = "Team 1", logoUrl = "https://cdn.pandascore.co/images/team/image/3213/220px_team_liquidlogo_square.png"),
            Team(id = 0, name = "Team 2", logoUrl = "https://cdn.pandascore.co/images/team/image/3240/208px_mouz_2021_allmode.png")
        ),
        players = List(10) {
            Player(
                id = 0,
                name = "Nome Jogador",
                nickName = "Nickname",
                pictureUrl = "https://cdn.pandascore.co/images/player/image/17527/nitr0_iem_sydney_2019.png"
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

    val matchesUIState = MutableStateFlow<UiState>(UiState.Loading)

    init {
        viewModelScope.launch {
            delay(5000)
            matchesUIState.value = UiState.Success(List(5) { fakeMatch })
            delay(5000)
            matchesUIState.value = UiState.Error(Throwable())
            delay(5000)
            matchesUIState.value = UiState.Loading
            delay(5000)
            matchesUIState.value = UiState.Success(List(5) { fakeMatch })
        }
    }
}