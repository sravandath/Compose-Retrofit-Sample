package com.hyparz.composeretrofitsample.di

import com.hyparz.composeretrofitsample.core.domain.repository.HomeRepository
import com.hyparz.composeretrofitsample.core.network.ApiInterface
import com.hyparz.composeretrofitsample.core.network.AppDispatchers
import com.hyparz.composeretrofitsample.core.network.Dispatcher
import com.hyparz.composeretrofitsample.data.repository.HomeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideHomeRepository(
        @Dispatcher(AppDispatchers.IO)  ioDispatcher: CoroutineDispatcher,
        api: ApiInterface
    ) :HomeRepository= HomeRepositoryImpl(ioDispatcher,api)


}