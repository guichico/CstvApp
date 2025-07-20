package com.codechallenge.repository.matches

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.codechallenge.model.Match
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MatchesPagingSource(
    private val matchesRepository: MatchesRepository,
    private val upcomingMaxDate: String
) : PagingSource<Int, Match>() {

    override fun getRefreshKey(state: PagingState<Int, Match>): Int? =
        state.anchorPosition

    override val keyReuseSupported: Boolean = true

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Match> = withContext(Dispatchers.IO) {
        try {
            val currentPage = if ((params.key ?: 0) > 0) params.key!! else 1

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
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}