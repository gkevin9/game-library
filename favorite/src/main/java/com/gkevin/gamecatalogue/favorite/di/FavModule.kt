package com.gkevin.gamecatalogue.favorite.di

import com.gkevin.gamecatalogue.favorite.FavoriteDynamicViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favModule = module {
    viewModel { FavoriteDynamicViewModel(get()) }
}