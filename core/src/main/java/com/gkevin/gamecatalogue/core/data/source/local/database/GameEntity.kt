package com.gkevin.gamecatalogue.core.data.source.local.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favGame")
data class GameEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "poster")
    val poster: String,

    @ColumnInfo(name = "isFav")
    val isFav: Boolean = true
)
