package com.gkevin.gamecatalogue

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.gkevin.gamecatalogue.core.domain.model.Game
import com.gkevin.gamecatalogue.core.domain.usecase.GameUseCase

class MainViewModel(private val gameUseCase: GameUseCase): ViewModel() {

    fun getGames(platform: String = PC): LiveData<List<Game>> {
        return LiveDataReactiveStreams.fromPublisher(gameUseCase.getTopGame(platform))
    }
//    val arrayGame = LiveDataReactiveStreams.fromPublisher(gameUseCase.getTopGame(PC))

    companion object {
        const val XBOXONE = "1"
        const val PC = "4"
        const val PS4 = "18"
        const val NSWITCH = "7"
    }
}