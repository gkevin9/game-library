package com.gkevin.gamecatalogue.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gkevin.gamecatalogue.core.domain.model.Game
import com.gkevin.gamecatalogue.core.domain.model.GameDetail
import com.gkevin.gamecatalogue.core.domain.usecase.GameUseCase

class DetailViewModel(private val useCase: GameUseCase): ViewModel() {
    private var _game = MutableLiveData<Game>()
    val game: LiveData<Game>
        get() = _game

    fun getDetail(): LiveData<GameDetail> {
        return LiveDataReactiveStreams.fromPublisher(useCase.getGameDetail(_game.value?.id?: 0))
    }

    fun getDlc(): LiveData<List<Game>> {
        return LiveDataReactiveStreams.fromPublisher(useCase.getDlcGame(_game.value?.id?: 0))
    }

    fun changeDetail(game: Game) {
        _game.postValue(game)
    }

    fun insertFavGame() {
        _game.value?.isFav = true
        _game.value?.let { useCase.insertFavGame(it) }
    }

    fun removeFavGame() {
        _game.value?.isFav = false
        _game.value?.let { useCase.deleteFavGame(it) }
    }

    fun insertGame(game: Game) {
        _game.value = game
    }

    fun isGameFav(): Boolean {
        return _game.value?.isFav?: false
    }
}