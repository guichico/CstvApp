package com.codechallenge.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

// TODO To be reviewed
@Parcelize
@Serializable
data class Match(
    val id: Long,
    val teams: List<Team>,
    val players: List<Player>,
    val date: String,
    val league: League,
    val serie: Serie,
) : Parcelable
