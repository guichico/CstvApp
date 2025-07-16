package com.codechallenge.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Immutable
data class Typography(
    val robotoTitleLarge: TextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Medium,
        fontSize = 32.sp,
        lineHeight = 40.sp
    ),
    val robotoTitleMedium: TextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        lineHeight = 24.sp
    ),
    val robotoRegular1: TextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Normal,
        fontSize = 8.sp
    ),
    val robotoRegular2: TextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp
    ),
    val robotoRegular3: TextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    val robotoBold1: TextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    val robotoBold2: TextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp
    ),
    val robotoBold3: TextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Bold,
        fontSize = 8.sp
    ),
)

val LocalTypography = staticCompositionLocalOf { Typography() }