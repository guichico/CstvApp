package com.codechallenge.domain

import com.codechallenge.repository.players.PlayersRepository
import javax.inject.Inject

class GetPlayersUseCase @Inject constructor(
    private val playersRepository: PlayersRepository
) {
    operator fun invoke(teamId: Long) = playersRepository.getPlayers(teamId)
}