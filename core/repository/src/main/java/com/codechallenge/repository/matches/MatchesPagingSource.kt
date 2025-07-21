package com.codechallenge.repository.matches

import MatchesSourceType
import RunningMatches
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.codechallenge.model.Match
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class MatchesPagingSource(
    private val matchesRepository: MatchesRepository,
    private val latestDate: String
) : PagingSource<Int, Match>() {

    companion object {
        private const val STARTING_PAGE = 1
    }

    private var currentMatchesType: MatchesSourceType = RunningMatches(matchesRepository)

    override fun getRefreshKey(state: PagingState<Int, Match>): Int? {
        currentMatchesType = RunningMatches(matchesRepository)
        return STARTING_PAGE
    }

    override val keyReuseSupported: Boolean = true

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Match> = withContext(Dispatchers.IO) {
        try {
            val currentPage = params.key ?: STARTING_PAGE

            /* TODO In case to fetch with only one request but problem with different sorting
            val matches = matchesRepository.getMatches(
                pageSize = params.loadSize,
                pageNumber = currentPage,
                upcomingMaxDate = upcomingMaxDate,
            )

            LoadResult.Page(
                data = matches,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (matches.isEmpty()) null else currentPage + 1
            )
            */

            /* TODO: Spec doubt - If we can assume the running matches list size are never bigger than 10
             *  otherwise it will have a problem with pagination so then the next source request needs to be executed after the first one
             */
            var matchesSize: Int
            val matches = if (currentMatchesType.runConcurrentWithNext) {
                val matches2Deferred = async(start = CoroutineStart.LAZY) {
                    currentMatchesType = currentMatchesType.nextSource
                    currentMatchesType.loadMatches(params.loadSize, currentPage, latestDate)
                }
                val matches1Deferred = async(start = CoroutineStart.LAZY) {
                    val match1CurrentMatchesType = currentMatchesType
                    matches2Deferred.start()
                    match1CurrentMatchesType.loadMatches(params.loadSize, currentPage, latestDate)
                }

                matches1Deferred.start()

                matches1Deferred.await() + matches2Deferred.await().also { matchesSize = it.size }
            } else {
                currentMatchesType.loadMatches(params.loadSize, currentPage, latestDate)
                    .also { matchesSize = it.size }
            }

            val isEndOfTheList = matchesSize < params.loadSize

            val nextKey = when {
                currentMatchesType.isLastSource && isEndOfTheList -> null
                isEndOfTheList -> STARTING_PAGE
                else -> currentPage + 1
            }

            if (isEndOfTheList) {
                currentMatchesType = currentMatchesType.nextSource
            }

            LoadResult.Page(
                data = matches,
                prevKey = if (currentPage == STARTING_PAGE) null else currentPage - 1,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}