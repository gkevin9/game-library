package com.gkevin.gamecatalogue.core.domain.repository

import com.gkevin.gamecatalogue.core.domain.model.Game
import com.gkevin.gamecatalogue.core.domain.model.GameDetail
import io.reactivex.Flowable

interface IGameRepository {
    fun getTopGame(platform: String, ordering: String, page: Int): Flowable<List<Game>>
    fun getDlcGame(id: Int): Flowable<List<Game>>
    fun getGameDetail(id: Int): Flowable<GameDetail>

    fun insertFavGame(game: Game)
    fun deleteFavGame(game: Game)
    fun getFavGame(): Flowable<List<Game>>
}