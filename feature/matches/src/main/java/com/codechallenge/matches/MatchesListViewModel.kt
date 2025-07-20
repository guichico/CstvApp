package com.codechallenge.matches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codechallenge.model.League
import com.codechallenge.model.Match
import com.codechallenge.model.Serie
import com.codechallenge.model.Team
import dagger.hilt.android.lifecycle.HiltViewModel
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

    val matchesUIState = MutableStateFlow<MatchesUiState>(MatchesUiState.Loading)

    init {
        viewModelScope.launch {
            matchesUIState.value = MatchesUiState.Success(List(5) { fakeMatch })
        }
    }
}