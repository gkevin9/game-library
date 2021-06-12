package com.gkevin.gamecatalogue.core.di

import androidx.room.Room
import com.gkevin.gamecatalogue.core.data.GameRepository
import com.gkevin.gamecatalogue.core.data.source.local.LocalDataSource
import com.gkevin.gamecatalogue.core.data.source.local.database.GameDatabase
import com.gkevin.gamecatalogue.core.data.source.remote.RemoteDataSource
import com.gkevin.gamecatalogue.core.data.source.remote.network.ApiService
import com.gkevin.gamecatalogue.core.domain.repository.IGameRepository
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
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
        val hostname = "api.rawg.io"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/R+V29DqDnO269dFhAAB5jMlZHepWpDGuoejXJjprh7A=")
            .build()

        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
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
    val passphrase: ByteArray = SQLiteDatabase.getBytes("password".toCharArray())
    val factory = SupportFactory(passphrase)
    single {
        Room.databaseBuilder(
            androidContext(),
            GameDatabase::class.java,
            "favGameDb.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val repositoryModule = module {
    single { RemoteDataSource(get()) }
    single { LocalDataSource(get()) }
    single<IGameRepository> {
        GameRepository(get(), get())
    }
}