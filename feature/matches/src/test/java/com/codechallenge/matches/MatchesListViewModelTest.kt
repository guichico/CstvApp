package com.codechallenge.matches

import androidx.paging.testing.asSnapshot
import com.codechallenge.common.string.StringProvider
import com.codechallenge.matches.model.toUIModel
import com.codechallenge.test.AbstractTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import kotlin.test.assertEquals

class MatchesListViewModelTest : AbstractTest() {

    @Mock
    private lateinit var stringProvider: StringProvider

    private val matchesRepository = TestMatchesRepository()
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
    fun testMatchesList() = runTest {
        val expectedMatches = List(10) { fakeMatch.toUIModel(stringProvider) }

        val matchesSnapshot = viewModel.matchesPager.asSnapshot()

        assertEquals(
            expected = expectedMatches,
            actual = matchesSnapshot
        )
    }
}