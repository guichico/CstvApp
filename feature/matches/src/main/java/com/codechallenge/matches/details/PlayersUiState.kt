package com.codechallenge.matches.details

import com.codechallenge.model.Player

sealed class PlayersUiState {
    object Loading : PlayersUiState()
    data class Success(val teamAPlayers: List<Player>, val teamBPlayers: List<Player>) : PlayersUiState()
    data class Error(val exception: Throwable) : PlayersUiState()
}