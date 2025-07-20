package com.codechallenge.network.model

import com.codechallenge.model.Player
import com.google.gson.annotations.SerializedName

data class NPlayer(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("first_name")
    val firstName: String? = null,
    @SerializedName("last_name")
    val lastLame: String? = null,
    @SerializedName("image_url")
    val imgUrl: String? = null,
)

fun NPlayer.toModel() =
    Player(
        id = this.id,
        name = "${this.firstName?.trim()} ${this.lastLame?.trim()}",
        nickName = this.name?.trim(),
        pictureUrl = this.imgUrl
    )