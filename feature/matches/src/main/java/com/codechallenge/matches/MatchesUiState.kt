package com.codechallenge.matches

import com.codechallenge.model.Match

sealed class MatchesUiState {
    object Loading : MatchesUiState()
    data class Success(val matches: List<Match>) : MatchesUiState()
    data class Error(val exception: Throwable) : MatchesUiState()
}
