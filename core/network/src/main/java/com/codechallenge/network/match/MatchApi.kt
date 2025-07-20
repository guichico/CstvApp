package com.codechallenge.network.match

import com.codechallenge.network.model.NMatch
import retrofit2.http.GET
import retrofit2.http.Query

interface MatchApi {
    @GET("matches")
    suspend fun getMatches(
        @Query("page[size]") pageSize: Int,
        @Query("page[number]") pageNumber: Int,
        @Query("filter[status]") status: String,
        @Query("range[begin_at]") dates: String,
        @Query("sort") sort: String,
    ): List<NMatch>

    @GET("matches/running")
    suspend fun getRunningMatches(
        @Query("page[size]") pageSize: Int,
        @Query("page[number]") pageNumber: Int,
        @Query("sort") sort: String,
    ): List<NMatch>

    @GET("matches/upcoming")
    suspend fun getUpcomingMatches(
        @Query("page[size]") pageSize: Int,
        @Query("page[number]") pageNumber: Int,
        @Query("sort") sort: String,
    ): List<NMatch>

    @GET("matches/past")
    suspend fun getPastMatches(
        @Query("page[size]") pageSize: Int,
        @Query("page[number]") pageNumber: Int,
        @Query("sort") sort: String,
    ): List<NMatch>
}
