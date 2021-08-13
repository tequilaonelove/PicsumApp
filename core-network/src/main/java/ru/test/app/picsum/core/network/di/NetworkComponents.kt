package ru.test.app.picsum.core.network.di

import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.test.app.picsum.core.network.BuildConfig
import ru.test.app.picsum.core.network.api.PicsApi
import java.time.Duration
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Component(modules = [NetworkModule::class])
@Singleton
interface NetworkComponents {

    fun api(): PicsApi

    @Component.Builder
    interface Builder {
        fun build(): NetworkComponents
    }

}

@Module
abstract class NetworkModule {

    companion object {
        private const val BASE_URL = "https://picsum.photos/"

        @Provides
        @Singleton
        fun provideApi(): PicsApi = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level =
                            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BASIC
                            else HttpLoggingInterceptor.Level.NONE
                    })
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .build()
            )
            .build()
            .create(PicsApi::class.java)
    }
}