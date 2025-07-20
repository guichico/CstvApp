package com.codechallenge.repository.players

import com.codechallenge.model.Player
import com.codechallenge.network.model.NPlayer
import com.codechallenge.network.model.toModel
import com.codechallenge.network.player.PlayerApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface PlayersRepository {
    fun getPlayers(teamId: Long): Flow<List<Player>>
}

class PlayersRepositoryImpl(
    private val playerApi: PlayerApi
) : PlayersRepository {

    override fun getPlayers(teamId: Long): Flow<List<Player>> =
        flow {
            emit(
                playerApi.getPlayers(teamId)
                    .map(NPlayer::toModel)
            )
        }
}