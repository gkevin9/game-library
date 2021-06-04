package com.gkevin.gamecatalogue

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.gkevin.gamecatalogue.core.domain.usecase.GameUseCase

class MainViewModel(private val gameUseCase: GameUseCase): ViewModel() {
    val arrayGame = LiveDataReactiveStreams.fromPublisher(gameUseCase.getTopGame())

    val favGame = LiveDataReactiveStreams.fromPublisher(gameUseCase.getFavGame())

    val arrayGameXboxOne = LiveDataReactiveStreams.fromPublisher(gameUseCase.getGameWithPlatform(
        XBOXONE))

    companion object {
        private const val XBOXONE = 1
        private const val PC = 4
        private const val PS4 = 18
        private const val NSWITCH = 7
    }
}