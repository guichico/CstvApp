package com.codechallenge.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Player(
    val id: Long,
    val name: String? = null,
    val nickName: String? = null,
    val pictureUrl: String? = null
) : Parcelable