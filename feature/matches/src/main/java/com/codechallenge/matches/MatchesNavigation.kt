package com.codechallenge.matches

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.codechallenge.model.Match
import kotlinx.serialization.Serializable

@Serializable
object MatchesGraph

@Serializable
object MatchesListScreenRoute

fun NavGraphBuilder.matchesGraph(
    onMatchClick: (Match) -> Unit,
    matchDetailsNavigation: NavGraphBuilder.() -> Unit,
) {
    navigation<MatchesGraph>(startDestination = MatchesListScreenRoute) {
        composable<MatchesListScreenRoute> {
            MatchesListScreen(
                onMatchClick = onMatchClick
            )
        }
        matchDetailsNavigation()
    }
}