package com.codechallenge.repository.matches

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.codechallenge.model.Match
import com.codechallenge.network.match.MatchApi
import com.codechallenge.network.model.toModel
import kotlinx.coroutines.flow.Flow

interface MatchesRepository {

    fun getMatches(pageSize: Int, latestDate: String): Flow<PagingData<Match>>

    suspend fun getRunningMatches(pageSize: Int, pageNumber: Int, latestDate: String): List<Match>
    suspend fun getUpcomingMatches(pageSize: Int, pageNumber: Int, latestDate: String): List<Match>
    suspend fun getPastMatches(pageSize: Int, pageNumber: Int, latestDate: String): List<Match>
}

class MatchesRepositoryImpl(
    private val matchApi: MatchApi
) : MatchesRepository {

    enum class MatchApiStatus(val value: String) {
        RUNNING("running"),
        NOT_STARTED("not_started"),
        FINISHED("finished")
    }

    companion object {
        private const val EARLIEST_DATE = "1990-01-01"
        private const val SORTING_ASCENDING = "begin_at"
        private const val SORTING_DESCENDING = "-begin_at"
    }

    override fun getMatches(pageSize: Int, latestDate: String) =
        Pager(
            config = PagingConfig(
                initialLoadSize = pageSize,
                pageSize = pageSize,
            )
        ) {
            MatchesPagingSource(
                matchesRepository = this,
                latestDate = latestDate
            )
        }
            .flow

    private suspend fun getMatches(
        pageSize: Int,
        pageNumber: Int,
        status: MatchApiStatus,
        latestDate: String,
        sort: String
    ): List<Match> =
        matchApi.getMatches(
            pageSize = pageSize,
            pageNumber = pageNumber,
            status = status.value,
            dateRange = "$EARLIEST_DATE, $latestDate",
            sort = sort
        )
            .filter { nMatch -> nMatch.teams.size >= 2 } // TODO Why is some matches without two teams?
            .map { it.toModel() }

    override suspend fun getRunningMatches(pageSize: Int, pageNumber: Int, latestDate: String): List<Match> =
        getMatches(pageSize = pageSize, pageNumber = pageNumber, latestDate = latestDate, status = MatchApiStatus.RUNNING, sort = SORTING_DESCENDING)

    override suspend fun getUpcomingMatches(pageSize: Int, pageNumber: Int, latestDate: String): List<Match> =
        getMatches(
            pageSize = pageSize,
            pageNumber = pageNumber,
            latestDate = latestDate,
            status = MatchApiStatus.NOT_STARTED,
            sort = SORTING_ASCENDING
        )

    override suspend fun getPastMatches(pageSize: Int, pageNumber: Int, latestDate: String): List<Match> =
        getMatches(pageSize = pageSize, pageNumber = pageNumber, latestDate = latestDate, status = MatchApiStatus.FINISHED, sort = SORTING_DESCENDING)

}