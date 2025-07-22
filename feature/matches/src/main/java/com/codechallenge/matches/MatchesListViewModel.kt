package com.codechallenge.matches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.codechallenge.common.string.StringProvider
import com.codechallenge.matches.model.toUIModel
import com.codechallenge.repository.matches.MatchesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MatchesListViewModel @Inject constructor(
    matchesRepository: MatchesRepository,
    stringProvider: StringProvider
) : ViewModel() {

    companion object {
        const val PAGE_SIZE = 10
        const val UPCOMING_DAYS = 2L
    }

    val matchesPager = matchesRepository.getMatches(
        pageSize = PAGE_SIZE,
        latestDate = LocalDate.now().plusDays(UPCOMING_DAYS).toString()
    )
        .map { pagingData ->
            pagingData.map { match -> match.toUIModel(stringProvider) }
        }
        .flowOn(Dispatchers.Default)
        .cachedIn(viewModelScope)
}
