package com.gkevin.gamecatalogue

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.gkevin.gamecatalogue.core.domain.model.Game
import com.gkevin.gamecatalogue.core.domain.usecase.GameUseCase

class MainViewModel(private val gameUseCase: GameUseCase): ViewModel() {

    fun getGames(platform: String = PC, ordering: String = "", page: Int = 1): LiveData<List<Game>> {
        return LiveDataReactiveStreams.fromPublisher(gameUseCase.getTopGame(platform, ordering, page))
    }

    companion object {
        const val XBOXONE = "1"
        const val PC = "4"
        const val PS4 = "18"
        const val NSWITCH = "7"

        const val ORDERING_RELEASED_ASC = "-released"
        const val ORDERING_NAME = "name"
        const val ORDERING_RATING_ASC = "-rating"
    }
}