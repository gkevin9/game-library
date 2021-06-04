package com.gkevin.gamecatalogue.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GameDetailResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("released")
    val released: String,

    @field:SerializedName("background_image")
    val background_image: String,

    @field:SerializedName("rating")
    val rating: Number,

    @field:SerializedName("description")
    val description: String
)
