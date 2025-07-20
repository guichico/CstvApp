package com.codechallenge.matches.details

import android.os.Parcelable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.codechallenge.matches.CustomNavType
import com.codechallenge.matches.model.MatchUI
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Serializable
data class MatchDetailsScreenRoute(val matchDetailsScreenParameters: MatchDetailsScreenParameters)

@Parcelize
@Serializable
data class MatchDetailsScreenParameters(val match: MatchUI) : Parcelable

fun NavController.navigateToMatchDetails(match: MatchUI) {
    navigate(route = MatchDetailsScreenRoute(MatchDetailsScreenParameters(match)))
}

fun NavGraphBuilder.matchDetailsScreen(
    onBackClick: () -> Unit,
) {
    composable<MatchDetailsScreenRoute>(
        typeMap = mapOf(
            typeOf<MatchDetailsScreenParameters>() to CustomNavType(
                MatchDetailsScreenParameters::class.java,
                MatchDetailsScreenParameters.serializer()
            )
        ),
    ) {
        MatchDetailsScreen(
            onBackClick = onBackClick
        )
    }
}