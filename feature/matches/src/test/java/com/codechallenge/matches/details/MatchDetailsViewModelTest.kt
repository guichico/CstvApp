package com.codechallenge.matches.details

import com.codechallenge.matches.fakePlayers1
import com.codechallenge.matches.fakePlayers2
import com.codechallenge.matches.model.MatchUI
import com.codechallenge.model.Team
import com.codechallenge.test.AbstractTest
import com.codechallenge.test.SavedStateHandleMock
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

private val fakeMatchUiSuccess =
    MatchUI(
        id = 0,
        teamA = Team(id = 1),
        teamB = Team(id = 2)
    )

private val fakeMatchUiError =
    MatchUI(
        id = 0,
        teamA = Team(id = 3),
        teamB = Team(id = 4)
    )

class MatchDetailsViewModelTest : AbstractTest() {
    private lateinit var playerRepository: TestPlayerRepository
    private lateinit var viewModel: MatchDetailsViewModel

    fun createMocks(matchUI: MatchUI) {
        val savedStateHandleMock = SavedStateHandleMock.create(MatchDetailsScreenRoute(MatchDetailsScreenParameters(matchUI)))

        playerRepository = TestPlayerRepository()

        viewModel = MatchDetailsViewModel(
            savedStateHandle = savedStateHandleMock,
            playerRepository = playerRepository
        )
    }

    @Test
    fun testPlayersUIStateLoading() = runTest {
        createMocks(fakeMatchUiSuccess)

        backgroundScope.launch { viewModel.playersUIState.collect() }

        val playersUiStateLoading = viewModel.playersUIState.first()

        assertIs<PlayersUiState.Loading>(playersUiStateLoading)
    }

    @Test
    fun testPlayersUIStateSuccess() = runTest {
        createMocks(fakeMatchUiSuccess)

        backgroundScope.launch { viewModel.playersUIState.collect() }

        val playersUiStateLoading = viewModel.playersUIState.first()

        assertIs<PlayersUiState.Loading>(playersUiStateLoading)

        val playersUiStateSuccess = viewModel.playersUIState.drop(1).first()

        assertEquals(
            expected = PlayersUiState.Success(
                teamAPlayers = fakePlayers1,
                teamBPlayers = fakePlayers2
            ),
            actual = playersUiStateSuccess
        )
    }

    @Test
    fun testPlayersUIStateError() = runTest {
        createMocks(fakeMatchUiError)

        backgroundScope.launch { viewModel.playersUIState.collect() }

        val playersUiStateLoading = viewModel.playersUIState.first()

        assertEquals(
            expected = PlayersUiState.Loading,
            actual = playersUiStateLoading
        )

        val playersUiStateError = viewModel.playersUIState.drop(1).first()
        assertIs<PlayersUiState.Error>(playersUiStateError)
    }
}