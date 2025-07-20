package com.codechallenge.model

import android.os.Parcelable
import com.codechallenge.model.converters.LocalDateTimeSerializer
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

enum class MatchStatus(val value: String) {
    IN_PROGRESS("running"),
    SCHEDULED("not_started"),
    ENDED("finished");

    companion object {
        fun fromString(value: String?): MatchStatus? {
            return entries.firstOrNull { it.value == value }
        }
    }
}

@Parcelize
@Serializable
data class Match(
    val id: Long,
    val teamA: Team? = null,
    val teamB: Team? = null,
    @Serializable(with = LocalDateTimeSerializer::class)
    val date: LocalDateTime? = null,
    val league: League? = null,
    val serie: Serie? = null,
    val status: MatchStatus? = null
) : Parcelable
