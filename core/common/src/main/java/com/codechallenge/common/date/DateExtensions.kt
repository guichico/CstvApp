package com.codechallenge.common.date

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

private fun LocalDateTime.fromUTCToLocalDateTime(): ZonedDateTime =
    this.atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.systemDefault())

private fun LocalDateTime.fromUTCToLocalDate(): LocalDate =
    this.fromUTCToLocalDateTime().toLocalDate()

private fun LocalDateTime.isDateTimeToday(): Boolean =
    this.fromUTCToLocalDate() == LocalDate.now()

private fun LocalDateTime.isCurrentYear(): Boolean =
    this.fromUTCToLocalDate().year == LocalDate.now().year

private fun LocalDateTime.formatDate(pattern: String) =
    DateTimeFormatter.ofPattern(pattern, Locale.getDefault()).format(this.fromUTCToLocalDateTime())

fun LocalDateTime.formatDayOfWeek(localizedTodayString: String): String {
    val isToday = this.isDateTimeToday()
    val dayPattern = if (isToday) "" else "E"

    val formattedDateTime = this.formatDate("$dayPattern, HH:mm").replaceFirstChar { it.uppercase() }

    return if (isToday) "$localizedTodayString$formattedDateTime" else formattedDateTime
}

private fun LocalDateTime.formatDayMonthWithoutYear(): String = this.formatDate("dd.MM HH:mm")

private fun LocalDateTime.formatDayMonthTimeWithYear(): String = this.formatDate("dd.MM.YYYY HH:mm")

fun LocalDateTime.formatDayMonthTime(): String =
    if (this.isCurrentYear()) formatDayMonthWithoutYear() else formatDayMonthTimeWithYear()
