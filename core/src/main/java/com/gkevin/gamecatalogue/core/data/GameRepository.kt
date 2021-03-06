package com.gkevin.gamecatalogue.core.data

import com.gkevin.gamecatalogue.core.data.source.local.LocalDataSource
import com.gkevin.gamecatalogue.core.data.source.remote.RemoteDataSource
import com.gkevin.gamecatalogue.core.domain.model.Game
import com.gkevin.gamecatalogue.core.domain.model.GameDetail
import com.gkevin.gamecatalogue.core.domain.repository.IGameRepository
import com.gkevin.gamecatalogue.core.utils.DataMapper
import io.reactivex.Flowable

class GameRepository(
    private val remoteRepository: RemoteDataSource,
    private val localRepository: LocalDataSource
    ): IGameRepository {

    override fun getTopGame(platform: String, ordering: String, page: Int): Flowable<List<Game>> {
        return remoteRepository.getTopGame(platform, ordering, page).map { DataMapper.mapResponseToModel(it) }
    }

    override fun getDlcGame(id: Int): Flowable<List<Game>> {
        return remoteRepository.getDlcGame(id).map { DataMapper.mapResponseToModel(it) }
    }

    override fun getGameDetail(id: Int): Flowable<GameDetail> {
        return remoteRepository.getGameDetail(id).map { DataMapper.mapResponseDetailToModel(it) }
    }

    override fun insertFavGame(game: Game) {
        localRepository.insertFavGame(game)
    }

    override fun deleteFavGame(game: Game) {
        localRepository.deleteFavGame(game)
    }

    override fun getFavGame(): Flowable<List<Game>> {
        return localRepository.getFavGame().map { DataMapper.mapEntityToModel(it) }
    }

}