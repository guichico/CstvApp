package com.codechallenge.cstvapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.codechallenge.matches.MatchesGraph
import com.codechallenge.matches.details.matchDetailsScreen
import com.codechallenge.matches.details.navigateToMatchDetails
import com.codechallenge.matches.matchesGraph

@Composable
internal fun CstvApp(
    navController: NavHostController
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            NavHost(
                navController = navController,
                startDestination = MatchesGraph
            ) {
                matchesGraph(
                    onMatchClick = navController::navigateToMatchDetails
                ) {
                    matchDetailsScreen(
                        onBackClick = navController::popBackStack
                    )
                }
            }
        }
    }
}