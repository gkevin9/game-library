package com.gkevin.gamecatalogue.core.domain.usecase

import com.gkevin.gamecatalogue.core.domain.model.Game
import com.gkevin.gamecatalogue.core.domain.model.GameDetail
import com.gkevin.gamecatalogue.core.domain.repository.IGameRepository
import io.reactivex.Flowable

class GameInteractor(private val gameRepository: IGameRepository): GameUseCase {
    override fun getTopGame(platform: String, ordering: String): Flowable<List<Game>> {
        return gameRepository.getTopGame(platform, ordering)
    }

    override fun getDlcGame(id: Int): Flowable<List<Game>> {
        return gameRepository.getDlcGame(id)
    }

    override fun getGameDetail(id: Int): Flowable<GameDetail> {
        return gameRepository.getGameDetail(id)
    }

    override fun insertFavGame(game: Game) {
        gameRepository.insertFavGame(game)
    }

    override fun deleteFavGame(game: Game) {
        gameRepository.deleteFavGame(game)
    }

    override fun getFavGame(): Flowable<List<Game>> {
        return gameRepository.getFavGame()
    }
}