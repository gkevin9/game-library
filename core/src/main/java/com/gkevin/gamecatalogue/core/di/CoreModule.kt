package com.gkevin.gamecatalogue.core.di

import androidx.room.Room
import com.gkevin.gamecatalogue.core.data.GameRepository
import com.gkevin.gamecatalogue.core.data.source.local.LocalDataSource
import com.gkevin.gamecatalogue.core.data.source.local.database.GameDatabase
import com.gkevin.gamecatalogue.core.data.source.remote.RemoteDataSource
import com.gkevin.gamecatalogue.core.data.source.remote.network.ApiService
import com.gkevin.gamecatalogue.core.domain.repository.IGameRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.rawg.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val databaseModule = module {
    factory { get<GameDatabase>().favGameDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            GameDatabase::class.java,
            "favGameDb.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val repositoryModule = module {
    single { RemoteDataSource(get()) }
    single { LocalDataSource(get()) }
    single<IGameRepository> {
        GameRepository(get(), get())
    }
}