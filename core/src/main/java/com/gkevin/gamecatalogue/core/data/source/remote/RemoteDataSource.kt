package com.gkevin.gamecatalogue.core.data.source.remote

import android.util.Log
import com.gkevin.gamecatalogue.core.data.source.remote.network.ApiService
import com.gkevin.gamecatalogue.core.data.source.remote.response.GameDetailResponse
import com.gkevin.gamecatalogue.core.data.source.remote.response.GameResponse
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class RemoteDataSource(private val apiService: ApiService) {
    fun getTopGame(): Flowable<List<GameResponse>> {
        val result = PublishSubject.create<List<GameResponse>>()

        val client = apiService.getListGames()
        client.subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe ({
                val gameArray = it.results
                result.onNext(if (gameArray.isNotEmpty()) gameArray else ArrayList())
            }, {
                Log.e(TAG, it.toString())
            })

        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun getDlcGame(id: Int): Flowable<List<GameResponse>> {
        val result = PublishSubject.create<List<GameResponse>>()

        val client = apiService.getListDlcGames(id)
        client.subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe ({
                val gameArray = it.results
                result.onNext(if (gameArray.isNotEmpty()) gameArray else ArrayList())
            }, {
                Log.e(TAG, it.toString())
            })

        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun getGameDetail(id: Int): Flowable<GameDetailResponse> {
        val result = PublishSubject.create<GameDetailResponse>()

        val client = apiService.getGameDetail(id)
        client.subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe ({
                result.onNext(it)
            }, {
                Log.e(TAG, it.toString())
            })

        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun getGameWithPlatform(platform: Int): Flowable<List<GameResponse>> {
        val result = PublishSubject.create<List<GameResponse>>()

        val client = apiService.getGameWithPlatform(platforms = platform)
        client.subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({
                val games = it.results
                result.onNext(games)
            }, {
                Log.e(TAG, it.toString())
            })

        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    companion object {
        private const val TAG = "RemoteDataSource"
    }
}