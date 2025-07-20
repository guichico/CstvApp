package com.codechallenge.repository.matches

import android.util.Log
import com.codechallenge.model.Match
import com.codechallenge.network.match.MatchApi
import com.codechallenge.network.model.toModel

interface MatchesRepository {
    suspend fun getMatches(pageSize: Int, pageNumber: Int, upcomingMaxDate: String): List<Match>
}

class MatchesRepositoryImpl(
    private val matchApi: MatchApi
) : MatchesRepository {

    companion object {
        private const val STATUS = "running,not_started,finished"
        private const val SORTING = "-status,-begin_at"
    }

    override suspend fun getMatches(pageSize: Int, pageNumber: Int, upcomingMaxDate: String): List<Match> =
        matchApi.getMatches(
            pageSize = pageSize,
            pageNumber = pageNumber,
            status = STATUS,
            dates = "1990-01-01, $upcomingMaxDate",
            sort = SORTING
        )
            .filter { nMatch ->
                if (nMatch.teams.size < 2) Log.d("TEST", "teams: $nMatch")
                nMatch.teams.size >= 2
            } // TODO Why is some matches without two teams?
            .map { it.toModel() }

}
