package com.gkevin.gamecatalogue.core.data.source.local

import android.util.Log
import com.gkevin.gamecatalogue.core.data.source.local.database.FavGameDao
import com.gkevin.gamecatalogue.core.data.source.local.database.GameEntity
import com.gkevin.gamecatalogue.core.domain.model.Game
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.runBlocking

class LocalDataSource(private val dao: FavGameDao) {
    fun insertFavGame(game: Game) {
        val entity = GameEntity(game.id, game.name, game.background_image)
        dao.insertGame(entity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }

    fun deleteFavGame(game: Game) {
        val entity = GameEntity(game.id, game.name, game.background_image)
        dao.deleteGame(entity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }

    fun getFavGame(): Flowable<List<GameEntity>> {
        val result = PublishSubject.create<List<GameEntity>>()

        val client = dao.getFavGame()
        client.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val gameArray = it
                    result.onNext(if (gameArray.isNotEmpty()) gameArray else ArrayList())
                }, {
                    Log.e(TAG, it.toString())
                })
        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    companion object {
        private const val TAG = "LocalDataSource"
    }
}