package com.gkevin.gamecatalogue.core.domain.model

data class GameDetail (
    val id: Int,
    val name: String,
    val released: String,
    val background_image: String,
    val rating: Number,
    val description: String
)