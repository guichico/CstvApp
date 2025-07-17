package com.codechallenge.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

// TODO To be reviewed
@Parcelize
@Serializable
data class Team(
    val id: Long,
    val name: String,
    val logoUrl: String
) : Parcelable