package com.codechallenge.matches

import androidx.paging.testing.asSnapshot
import com.codechallenge.common.string.StringProvider
import com.codechallenge.matches.model.toUIModel
import com.codechallenge.model.League
import com.codechallenge.model.Match
import com.codechallenge.model.MatchStatus
import com.codechallenge.model.Serie
import com.codechallenge.model.Team
import com.codechallenge.repository.matches.MatchesRepository
import com.codechallenge.test.AbstractTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.whenever
import java.time.LocalDateTime
import kotlin.test.assertEquals

class MatchesListViewModelTest : AbstractTest() {

    @Mock
    private lateinit var matchesRepository: MatchesRepository

    @Mock
    private lateinit var stringProvider: StringProvider

    private lateinit var viewModel: MatchesListViewModel

    @Before
    fun setup() {
        openMocks(this)

        viewModel = MatchesListViewModel(
            matchesRepository = matchesRepository,
            stringProvider = stringProvider
        )
    }

    @After
    fun tearDown() {
        closeMocks()
    }

    @Test
    fun testMatches() = runTest {
        whenever(stringProvider.getString(R.string.live)).thenReturn("Live")
        whenever(stringProvider.getString(R.string.today)).thenReturn("Today")

        val runningMatches = List(4) { fakeMatch(MatchStatus.IN_PROGRESS) }
        val upcomingMatches = List(8) { fakeMatch(MatchStatus.SCHEDULED) }
        val pastMatches = List(5) { fakeMatch(MatchStatus.ENDED) }

        whenever(matchesRepository.getRunningMatches(any(), any(), any())).thenReturn(runningMatches)
        whenever(matchesRepository.getUpcomingMatches(any(), eq(1), any())).thenReturn(upcomingMatches)
        whenever(matchesRepository.getPastMatches(any(), eq(1), any())).thenReturn(pastMatches)
        whenever(matchesRepository.getPastMatches(any(), eq(2), any())).thenReturn(emptyList())

        val matchesSnapshot = viewModel.matchesPager.asSnapshot {
            scrollTo(10)
        }

        val allMatches = (runningMatches + upcomingMatches + pastMatches)
            .map { it.toUIModel(stringProvider) }

        assertEquals(
            expected = allMatches,
            actual = matchesSnapshot
        )
    }

    @Test
    fun testMatches2() = runTest {
        whenever(stringProvider.getString(R.string.live)).thenReturn("Live")
        whenever(stringProvider.getString(R.string.today)).thenReturn("Today")

        val runningMatches = List(4) { fakeMatch(MatchStatus.IN_PROGRESS) }
        val upcomingMatches = List(10) { fakeMatch(MatchStatus.SCHEDULED) }
        val pastMatches = List(10) { fakeMatch(MatchStatus.ENDED) }

        whenever(matchesRepository.getRunningMatches(any(), any(), any())).thenReturn(runningMatches)

        whenever(matchesRepository.getUpcomingMatches(any(), eq(1), any())).thenReturn(upcomingMatches)
        whenever(matchesRepository.getUpcomingMatches(any(), eq(2), any())).thenReturn(upcomingMatches.take(5))

        whenever(matchesRepository.getPastMatches(any(), eq(1), any())).thenReturn(pastMatches)
        whenever(matchesRepository.getPastMatches(any(), eq(2), any())).thenReturn(pastMatches.take(5))

        val matchesSnapshot = viewModel.matchesPager.asSnapshot {
            scrollTo(20)
        }

        val allMatches = (runningMatches + upcomingMatches + upcomingMatches.take(5) + pastMatches + pastMatches.take(5))
            .map { it.toUIModel(stringProvider) }

        assertEquals(
            expected = allMatches,
            actual = matchesSnapshot
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