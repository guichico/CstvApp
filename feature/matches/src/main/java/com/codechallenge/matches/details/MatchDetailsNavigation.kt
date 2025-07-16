package com.codechallenge.matches.details

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object MatchDetailsScreenRoute

fun NavController.navigateToMatchDetails() {
    navigate(route = MatchDetailsScreenRoute)
}

fun NavGraphBuilder.matchDetailsScreen(
    onBackClick: () -> Unit,
) {
    composable<MatchDetailsScreenRoute> {
        MatchDetailsScreen(
            onBackClick = onBackClick
        )
    }
}