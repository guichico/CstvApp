package com.codechallenge.matches

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import kotlinx.serialization.Serializable

@Serializable
object MatchesGraph

@Serializable
object MatchesListScreenRoute

fun NavGraphBuilder.matchesGraph(
    onMatchClick: () -> Unit,
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