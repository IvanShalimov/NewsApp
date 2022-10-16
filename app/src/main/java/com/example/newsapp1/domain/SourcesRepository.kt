package com.example.newsapp1.domain

import com.example.newsapp1.di.qualifier.ApiKey
import com.example.newsapp1.domain.network.NewsService
import com.example.newsapp1.domain.network.models.SourcesResponse
import com.example.newsapp1.domain.network.models.TopHeadlinesResponse
import com.example.newsapp1.presentation.models.SourceItem
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class SourcesRepository
@Inject constructor(
    private val newsService: NewsService,
    @ApiKey private val apiKey: String
) {

    fun getSources(callback: Callback<SourcesResponse>) {
        val sourcesResponses = newsService.getSources(apiKey)
        sourcesResponses.enqueue(callback)
    }

    fun getTopHeaders(source: String, callback: Callback<TopHeadlinesResponse>) {
        val topHeadersResponse = newsService.getTopHeadlines(source, apiKey)
        topHeadersResponse.enqueue(callback)
    }
}