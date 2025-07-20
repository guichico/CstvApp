package com.codechallenge.network.model

import com.codechallenge.model.Serie
import com.codechallenge.network.adapters.LocalDateTimeAdapter
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class NSerie(
    @SerializedName("id")
    val id: Long,
    @SerializedName("begin_at")
    @JsonAdapter(LocalDateTimeAdapter::class)
    val beginAt: LocalDateTime? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("full_name")
    val fullName: String? = null,
)

fun NSerie.toModel() =
    Serie(
        id = this.id,
        name = this.name
    )