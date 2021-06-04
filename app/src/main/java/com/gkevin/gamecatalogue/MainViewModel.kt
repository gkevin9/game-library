package com.gkevin.gamecatalogue

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.gkevin.gamecatalogue.core.domain.usecase.GameUseCase

class MainViewModel(private val gameUseCase: GameUseCase): ViewModel() {
    val arrayGame = LiveDataReactiveStreams.fromPublisher(gameUseCase.getTopGame())

    val favGame = LiveDataReactiveStreams.fromPublisher(gameUseCase.getFavGame())
}