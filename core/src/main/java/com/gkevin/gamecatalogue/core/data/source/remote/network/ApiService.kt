package com.gkevin.gamecatalogue.core.data.source.remote.network

import com.gkevin.gamecatalogue.core.data.source.remote.response.ArrayGameResponse
import com.gkevin.gamecatalogue.core.data.source.remote.response.GameDetailResponse
import com.gkevin.gamecatalogue.core.data.source.remote.response.GameResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("games")
    fun getListGames(
        @Query("platforms") platforms: String,
        @Query("key") key: String = "5053d9a247c343709b7a015cb113bdca"
    ): Flowable<ArrayGameResponse>

    @GET("games/{id}/additions")
    fun getListDlcGames(
        @Path("id") id: Int,
        @Query("key") key: String = "5053d9a247c343709b7a015cb113bdca"
    ): Flowable<ArrayGameResponse>

    @GET("games/{id}")
    fun getGameDetail(
        @Path("id") id: Int,
        @Query("key") key: String = "5053d9a247c343709b7a015cb113bdca"
    ): Flowable<GameDetailResponse>

}