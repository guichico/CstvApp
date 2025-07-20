package com.codechallenge.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class League(
    val id: Long,
    val name: String? = null,
    val imgUrl: String? = null,
) : Parcelable