package com.codechallenge.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

// TODO To be reviewed
@Parcelize
@Serializable
data class Serie(
    val id: Long,
    val name: String
) : Parcelable