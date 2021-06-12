package com.gkevin.gamecatalogue.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ArrayGameResponse(
    @field:SerializedName("count")
    val count: Int,

    @field:SerializedName("results")
    val results: ArrayList<GameResponse>
)
