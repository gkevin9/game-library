package com.gkevin.gamecatalogue.di

import com.gkevin.gamecatalogue.MainViewModel
import com.gkevin.gamecatalogue.core.domain.usecase.GameInteractor
import com.gkevin.gamecatalogue.core.domain.usecase.GameUseCase
import com.gkevin.gamecatalogue.detail.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val usecaseModule = module {
    factory<GameUseCase> { GameInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}