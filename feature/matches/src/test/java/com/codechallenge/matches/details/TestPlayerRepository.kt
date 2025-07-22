package com.codechallenge.matches.details

import com.codechallenge.matches.fakePlayers1
import com.codechallenge.matches.fakePlayers2
import com.codechallenge.repository.players.PlayersRepository
import kotlinx.coroutines.flow.flow

class TestPlayerRepository() : PlayersRepository {
    override fun getPlayers(teamId: Long) =
        when (teamId) {
            1L -> flow { emit(fakePlayers1) }
            2L -> flow { emit(fakePlayers2) }
            else -> flow { throw RuntimeException("Error") }
        }
}