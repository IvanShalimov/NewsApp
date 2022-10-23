package com.example.newsapp1.di.modules

import com.example.newsapp1.di.qualifier.ApiKey
import com.example.newsapp1.domain.network.NewsService
import com.example.newsapp1.presentation.mapper.SourcesItemMapper
import com.example.newsapp1.presentation.mapper.TopHeadlinesMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
object RetrofitModule {

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

    @Provides
    fun provideSourceMapper(): SourcesItemMapper {
        return SourcesItemMapper()
    }

    @Provides
    fun provideTopHeadlinesMapper(): TopHeadlinesMapper {
        return TopHeadlinesMapper()
    }
}