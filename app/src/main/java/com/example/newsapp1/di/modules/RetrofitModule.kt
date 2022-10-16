package com.example.newsapp1.di.modules

import com.example.newsapp1.di.qualifier.ApiKey
import com.example.newsapp1.domain.network.NewsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ActivityComponent::class)
object  RetrofitModule {

    @Provides
    fun provideNetworkService(): NewsService {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsService::class.java)
    }

    @ApiKey
    @Provides
    fun provideApiKey(): String {
        return "6ba37edae5004b5bb16ab6d1a3ae42cc"
    }
}