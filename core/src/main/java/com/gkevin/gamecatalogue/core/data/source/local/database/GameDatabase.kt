package com.gkevin.gamecatalogue.core.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [GameEntity::class], version = 1, exportSchema = false)
abstract class GameDatabase: RoomDatabase() {
    abstract fun favGameDao(): FavGameDao
}