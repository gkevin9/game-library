package com.gkevin.gamecatalogue

import android.app.Application
import com.gkevin.gamecatalogue.core.di.databaseModule
import com.gkevin.gamecatalogue.core.di.networkModule
import com.gkevin.gamecatalogue.core.di.repositoryModule
import com.gkevin.gamecatalogue.di.usecaseModule
import com.gkevin.gamecatalogue.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    usecaseModule,
                    viewModelModule,
                    databaseModule
                )
            )
        }
    }
}