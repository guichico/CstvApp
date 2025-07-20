package com.codechallenge.common.date

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun LocalDateTime.fromUTCToLocalDateTime(): ZonedDateTime =
    this.atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.systemDefault())

fun LocalDateTime.formatDayOfWeek(): String =
    DateTimeFormatter.ofPattern("E, HH:mm", Locale.getDefault())
        .format(this.fromUTCToLocalDateTime()).replaceFirstChar { it.uppercase() }

fun LocalDateTime.formatDayMonth(): String =
    DateTimeFormatter.ofPattern("dd.MM HH:mm", Locale.getDefault()).format(this.fromUTCToLocalDateTime())
