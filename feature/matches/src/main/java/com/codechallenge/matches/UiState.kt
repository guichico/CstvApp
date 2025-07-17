package com.codechallenge.matches

sealed class UiState {
    object Loading : UiState()
    data class Success<out T>(val data: T) : UiState()
    data class Error(val exception: Throwable) : UiState()
}