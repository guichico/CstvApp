package com.codechallenge.network.model

import com.codechallenge.model.League
import com.google.gson.annotations.SerializedName

data class NLeague(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("image_url")
    val imgUrl: String? = null,
)

fun NLeague.toModel(): League =
    League(
        id = this.id,
        name = this.name,
        imgUrl = this.imgUrl,
    )