package com.codechallenge.matches

import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import com.codechallenge.model.Match
import com.codechallenge.repository.matches.MatchesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TestMatchesRepository : MatchesRepository {
    override fun getMatches(
        pageSize: Int,
        latestDate: String
    ): Flow<PagingData<Match>> {
        val matches = List(10) { fakeMatch }

        return flow {
            emit(
                PagingData.from(
                    data = matches,
                    sourceLoadStates = LoadStates(
                        refresh = LoadState.Loading,
                        prepend = LoadState.NotLoading(endOfPaginationReached = false),
                        append = LoadState.NotLoading(endOfPaginationReached = false)
                    )
                )
            )

            emit(
                PagingData.from(
                    data = matches,
                    sourceLoadStates = LoadStates(
                        refresh = LoadState.NotLoading(endOfPaginationReached = false),
                        prepend = LoadState.NotLoading(endOfPaginationReached = false),
                        append = LoadState.NotLoading(endOfPaginationReached = false)
                    )
                )
            )
        }
    }

    override suspend fun getRunningMatches(pageSize: Int, pageNumber: Int, latestDate: String): List<Match> {
        TODO("Not yet implemented")
    }

    override suspend fun getUpcomingMatches(pageSize: Int, pageNumber: Int, latestDate: String): List<Match> {
        TODO("Not yet implemented")
    }

    override suspend fun getPastMatches(pageSize: Int, pageNumber: Int, latestDate: String): List<Match> {
        TODO("Not yet implemented")
    }
}