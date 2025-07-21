package com.codechallenge.domain

import androidx.paging.PagingData
import com.codechallenge.model.Match
import com.codechallenge.repository.matches.MatchesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMatchesUseCase @Inject constructor(
    private val matchesRepository: MatchesRepository
) {
    operator fun invoke(pageSize: Int, latestDate: String): Flow<PagingData<Match>> =
        matchesRepository.getMatches(pageSize, latestDate)
}