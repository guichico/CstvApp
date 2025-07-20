package com.codechallenge.network.model

import com.codechallenge.model.Match
import com.codechallenge.model.MatchStatus
import com.codechallenge.network.adapters.LocalDateTimeAdapter
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class NMatch(
    @SerializedName("id")
    val id: Long,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("begin_at")
    @JsonAdapter(LocalDateTimeAdapter::class)
    val beginAt: LocalDateTime? = null,
    @SerializedName("scheduled_at")
    @JsonAdapter(LocalDateTimeAdapter::class)
    val scheduledAt: LocalDateTime? = null,
    @SerializedName("league")
    val league: NLeague? = null,
    @SerializedName("opponents")
    val teams: List<JsonTeamHelper> = emptyList(),
    @SerializedName("serie")
    val serie: NSerie? = null
)

fun NMatch.toModel(): Match {
    return Match(
        id = this.id,
        teamA = this.teams[0].team.toModel(),
        teamB = this.teams[1].team.toModel(),
        date = this.scheduledAt,
        league = this.league?.toModel(),
        serie = this.serie?.toModel(),
        status = MatchStatus.fromString(status)
    )
}
