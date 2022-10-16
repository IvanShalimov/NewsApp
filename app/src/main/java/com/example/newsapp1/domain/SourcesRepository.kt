package com.example.newsapp1.domain

import com.example.newsapp1.domain.network.NewsService
import com.example.newsapp1.domain.network.models.SourcesResponse
import com.example.newsapp1.domain.network.models.TopHeadlinesResponse
import com.example.newsapp1.presentation.models.SourceItem
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SourcesRepository {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val newsService: NewsService by lazy { retrofit.create(NewsService::class.java) }

    fun getSources(callback: Callback<SourcesResponse>) {
        val sourcesResponses = newsService.getSources("6ba37edae5004b5bb16ab6d1a3ae42cc")
        sourcesResponses.enqueue(callback)
    }

    fun getTopHeaders(source: String, callback: Callback<TopHeadlinesResponse>) {
        val topHeadersResponse = newsService.getTopHeadlines(source, "6ba37edae5004b5bb16ab6d1a3ae42cc")
        topHeadersResponse.enqueue(callback)
    }
}