package com.gkevin.gamecatalogue.core.data.source.local.database

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface FavGameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGame(game: GameEntity): Completable

    @Delete
    fun deleteGame(game: GameEntity): Completable

    @Query("select * from favGame")
    fun getFavGame(): Flowable<List<GameEntity>>
}