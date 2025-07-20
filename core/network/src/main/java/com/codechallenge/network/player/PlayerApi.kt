package com.codechallenge.network.player

import com.codechallenge.network.model.NPlayer
import retrofit2.http.GET
import retrofit2.http.Query

interface PlayerApi {
    @GET("players")
    suspend fun getPlayers(
        @Query("filter[team_id]") teamId: Long,
    ): List<NPlayer>
}