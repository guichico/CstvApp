package com.codechallenge.matches.details

import com.codechallenge.model.Match

sealed class MatchUiState {
    data class Loading(val initialMatch: Match) : MatchUiState()
    data class Success(val match: Match) : MatchUiState()
    data class Error(val exception: Throwable) : MatchUiState()
}