package com.codechallenge.repository.di

import com.codechallenge.network.match.MatchApi
import com.codechallenge.network.player.PlayerApi
import com.codechallenge.repository.matches.MatchesRepository
import com.codechallenge.repository.matches.MatchesRepositoryImpl
import com.codechallenge.repository.players.PlayersRepository
import com.codechallenge.repository.players.PlayersRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesMatchesRepository(matchApi: MatchApi): MatchesRepository = MatchesRepositoryImpl(matchApi)

    @Provides
    @Singleton
    fun providesPlayersRepository(playerApi: PlayerApi): PlayersRepository = PlayersRepositoryImpl(playerApi)
}