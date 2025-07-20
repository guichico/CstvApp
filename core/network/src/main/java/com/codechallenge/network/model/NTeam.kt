package com.codechallenge.network.model

import com.codechallenge.model.Team
import com.google.gson.annotations.SerializedName

data class JsonTeamHelper(
    @SerializedName("opponent")
    val team: NTeam,
)

data class NTeam(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("acronym")
    val acronym: String? = null,
    @SerializedName("image_url")
    val imgUrl: String? = null,
)

fun NTeam.toModel() =
    Team(
        id = this.id,
        name = this.name?.trim(),
        imgUrl = this.imgUrl,
    )