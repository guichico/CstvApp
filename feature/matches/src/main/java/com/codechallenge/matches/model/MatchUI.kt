package com.codechallenge.matches.model

import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import com.codechallenge.common.date.formatDayMonthTime
import com.codechallenge.common.date.formatDayOfWeek
import com.codechallenge.common.string.StringProvider
import com.codechallenge.designsystem.theme.LiveMatchColor
import com.codechallenge.designsystem.theme.MatchTimeColor
import com.codechallenge.matches.R
import com.codechallenge.matches.model.converters.ColorSerializer
import com.codechallenge.model.League
import com.codechallenge.model.Match
import com.codechallenge.model.MatchStatus
import com.codechallenge.model.Serie
import com.codechallenge.model.Team
import com.codechallenge.model.converters.LocalDateTimeSerializer
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Parcelize
@Serializable
data class MatchUI(
    val id: Long,
    val teamA: Team? = null,
    val teamB: Team? = null,
    @Serializable(with = LocalDateTimeSerializer::class)
    val date: LocalDateTime? = null,
    val dateFormatted: String? = null,
    @Serializable(with = ColorSerializer::class)
    val dateBgColor: @RawValue Color = MatchTimeColor,
    val league: League? = null,
    val serie: Serie? = null,
    val status: MatchStatus? = null,
    val leagueAndSerieLabel: String? = null,
) : Parcelable

private fun Match.getDateFormatted(stringProvider: StringProvider) = when (this.status) {
    MatchStatus.IN_PROGRESS -> stringProvider.getString(R.string.live)
    MatchStatus.SCHEDULED -> date?.formatDayOfWeek(stringProvider.getString(R.string.today)) ?: ""
    MatchStatus.ENDED -> date?.formatDayMonthTime() ?: ""
    else -> ""
}

fun Match.toUIModel(stringProvider: StringProvider) =
    MatchUI(
        id = this.id,
        teamA = this.teamA,
        teamB = this.teamB,
        date = this.date,
        dateFormatted = this.getDateFormatted(stringProvider),
        league = this.league,
        serie = this.serie,
        status = this.status,
        dateBgColor = if (this.status == MatchStatus.IN_PROGRESS) LiveMatchColor else MatchTimeColor,
        leagueAndSerieLabel = buildString {
            append(this@toUIModel.league?.name)
            if (this@toUIModel.league?.name?.isNotEmpty() == true && this@toUIModel.serie?.name?.isNotEmpty() == true) append(" - ")
            append(this@toUIModel.serie?.name)
        }
    )