package com.gkevin.gamecatalogue.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    val id: Int,
    val name: String,
    val released: String,
    val background_image: String,
    val rating: Number,
    var isFav: Boolean = false
): Parcelable
