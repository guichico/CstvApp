package com.codechallenge.matches.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.codechallenge.domain.GetPlayersUseCase
import com.codechallenge.matches.CustomNavType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import kotlin.reflect.typeOf

@HiltViewModel
class MatchDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getPlayers: GetPlayersUseCase,
) : ViewModel() {

    val matchParam = savedStateHandle.toRoute<MatchDetailsScreenRoute>(
        typeMap = mapOf(
            typeOf<MatchDetailsScreenParameters>() to CustomNavType(
                MatchDetailsScreenParameters::class.java,
                MatchDetailsScreenParameters.serializer()
            )
        )
    ).matchDetailsScreenParameters.match

    val playersUIState: StateFlow<PlayersUiState> =
        combine(
            // Must have two teams
            getPlayers(matchParam.teamA!!.id),
            getPlayers(matchParam.teamB!!.id)
        ) { teamA, teamB ->
            PlayersUiState.Success(
                teamAPlayers = teamA,
                teamBPlayers = teamB
            ) as PlayersUiState
        }
            .catch { e ->
                emit(PlayersUiState.Error(e) as PlayersUiState)
            }
            .flowOn(Dispatchers.IO)
            .stateIn(viewModelScope, SharingStarted.Lazily, PlayersUiState.Loading)
}
