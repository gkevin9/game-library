package com.gkevin.gamecatalogue.favorite

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.gkevin.gamecatalogue.core.domain.usecase.GameUseCase

class FavoriteDynamicViewModel(private val gameUseCase: GameUseCase): ViewModel() {
    val favGame = LiveDataReactiveStreams.fromPublisher(gameUseCase.getFavGame())
}