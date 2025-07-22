package com.codechallenge.repository

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult
import com.codechallenge.model.League
import com.codechallenge.model.Match
import com.codechallenge.model.MatchStatus
import com.codechallenge.model.Serie
import com.codechallenge.model.Team
import com.codechallenge.repository.matches.MatchesPagingSource
import com.codechallenge.repository.matches.MatchesRepository
import com.codechallenge.test.AbstractTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.whenever
import java.time.LocalDateTime
import kotlin.test.assertEquals

class MatchesPagingSourceTest : AbstractTest() {

    companion object {
        private const val PAGE_SIZE = 10
    }

    @Mock
    private lateinit var matchesRepository: MatchesRepository

    private lateinit var matchesPagingSource: MatchesPagingSource

    @Before
    fun setup() {
        openMocks(this)

        matchesPagingSource = MatchesPagingSource(
            matchesRepository = matchesRepository,
            latestDate = ""
        )
    }

    @After
    fun tearDown() {
        closeMocks()
    }

    @Test
    fun testGetMatchesHalfList() = runTest {
        val runningMatches = List(4) { fakeMatch(MatchStatus.IN_PROGRESS) }
        val upcomingMatches = List(8) { fakeMatch(MatchStatus.SCHEDULED) }
        val pastMatches = List(5) { fakeMatch(MatchStatus.ENDED) }

        whenever(matchesRepository.getRunningMatches(PAGE_SIZE, 1, "")).thenReturn(runningMatches)
        whenever(matchesRepository.getUpcomingMatches(PAGE_SIZE, 1, "")).thenReturn(upcomingMatches)
        whenever(matchesRepository.getPastMatches(PAGE_SIZE, 1, "")).thenReturn(pastMatches)
        whenever(matchesRepository.getPastMatches(PAGE_SIZE, 2, "")).thenReturn(emptyList())

        val params1 = PagingSource.LoadParams.Refresh(1, PAGE_SIZE, false)
        val params2 = PagingSource.LoadParams.Refresh(2, PAGE_SIZE, false)

        val loadResult1 = matchesPagingSource.load(params1)

        val expectedLoadResult1 = LoadResult.Page(
            data = (runningMatches + upcomingMatches),
            prevKey = null,
            nextKey = 2
        )

        assertEquals(
            expected = expectedLoadResult1,
            actual = loadResult1
        )

        val loadResult2 = matchesPagingSource.load(params1)

        val expectedLoadResult2 = LoadResult.Page(
            data = (runningMatches + upcomingMatches + pastMatches),
            prevKey = null,
            nextKey = 2
        )

        assertEquals(
            expected = expectedLoadResult2,
            actual = loadResult2
        )

        val loadResult3 = matchesPagingSource.load(params2)

        val expectedLoadResult3 = LoadResult.Page(
            data = emptyList<Match>(),
            prevKey = null,
            nextKey = 2
        )

        assertEquals(
            expected = expectedLoadResult3,
            actual = loadResult3
        )
    }

    @Test
    fun testGetMatchesFullList() = runTest {
        val runningMatches = List(4) { fakeMatch(MatchStatus.IN_PROGRESS) }
        val upcomingMatches = List(10) { fakeMatch(MatchStatus.SCHEDULED) }
        val pastMatches = List(10) { fakeMatch(MatchStatus.ENDED) }

        whenever(matchesRepository.getRunningMatches(PAGE_SIZE, 1, "")).thenReturn(runningMatches)
        whenever(matchesRepository.getUpcomingMatches(PAGE_SIZE, 1, "")).thenReturn(upcomingMatches)
        whenever(matchesRepository.getUpcomingMatches(PAGE_SIZE, 2, "")).thenReturn(upcomingMatches.take(5))
        whenever(matchesRepository.getPastMatches(PAGE_SIZE, 1, "")).thenReturn(pastMatches)
        whenever(matchesRepository.getPastMatches(PAGE_SIZE, 2, "")).thenReturn(pastMatches.take(5))

        val params1 = PagingSource.LoadParams.Refresh(1, PAGE_SIZE, false)
        val params2 = PagingSource.LoadParams.Refresh(2, PAGE_SIZE, false)

        val loadResult1 = matchesPagingSource.load(params1)

        val expectedLoadResult1 = LoadResult.Page(
            data = (runningMatches + upcomingMatches),
            prevKey = null,
            nextKey = 2
        )

        assertEquals(
            expected = expectedLoadResult1,
            actual = loadResult1
        )

        val loadResult2 = matchesPagingSource.load(params2)

        val expectedLoadResult2 = LoadResult.Page(
            data = upcomingMatches.take(5),
            prevKey = 1,
            nextKey = 1
        )

        assertEquals(
            expected = expectedLoadResult2,
            actual = loadResult2
        )

        val loadResult3 = matchesPagingSource.load(params1)

        val expectedLoadResult3 = LoadResult.Page(
            data = pastMatches,
            prevKey = null,
            nextKey = 2
        )

        assertEquals(
            expected = expectedLoadResult3,
            actual = loadResult3
        )

        val loadResult4 = matchesPagingSource.load(params2)

        val expectedLoadResult4 = LoadResult.Page(
            data = pastMatches.take(5),
            prevKey = 1,
            nextKey = null
        )

        assertEquals(
            expected = expectedLoadResult4,
            actual = loadResult4
        )
    }

    private fun fakeMatch(status: MatchStatus) =
        Match(
            id = 0,
            teamA = Team(id = 0, name = "Team 1", imgUrl = "https://cdn.pandascore.co/images/team/image/3213/220px_team_liquidlogo_square.png"),
            teamB = Team(id = 0, name = "Team 2", imgUrl = "https://cdn.pandascore.co/images/team/image/3240/208px_mouz_2021_allmode.png"),
            date = LocalDateTime.now(),
            league = League(
                id = 0,
                name = "League",
                imgUrl = ""
            ),
            serie = Serie(
                id = 0,
                name = "serie"
            ),
            status = status
        )
}