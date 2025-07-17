package com.codechallenge.model

// TODO To be reviewed
data class Match(
    val id: Long,
    val teams: List<Team>,
    val players: List<Player>,
    val date: String,
    val league: League,
    val serie: Serie,
)
