package com.gkevin.gamecatalogue.core.utils

import com.gkevin.gamecatalogue.core.data.source.local.database.GameEntity
import com.gkevin.gamecatalogue.core.data.source.remote.response.GameDetailResponse
import com.gkevin.gamecatalogue.core.data.source.remote.response.GameResponse
import com.gkevin.gamecatalogue.core.domain.model.Game
import com.gkevin.gamecatalogue.core.domain.model.GameDetail

object DataMapper {
    fun mapResponseToModel(input: List<GameResponse>): List<Game> {
        val result = ArrayList<Game>()
        input.map {
            val game = Game(
                id = it.id,
                name = it.name,
                released = it.released?: "",
                background_image = it.background_image,
                rating = it.rating
            )
            result.add(game)
        }
        return result
    }

    fun mapResponseDetailToModel(input: GameDetailResponse): GameDetail {
        return GameDetail(
            id = input.id,
            name = input.name,
            released = input.released,
            background_image = input.background_image,
            rating = input.rating,
            description = input.description
        )
    }

    fun mapEntityToModel(input: List<GameEntity>): List<Game> {
        val result = ArrayList<Game>()
        input.map {
            val game = Game(
                id = it.id,
                name = it.name,
                released = "",
                background_image = it.poster,
                rating = 0,
                    isFav = it.isFav
            )
            result.add(game)
        }

        return result
    }
}