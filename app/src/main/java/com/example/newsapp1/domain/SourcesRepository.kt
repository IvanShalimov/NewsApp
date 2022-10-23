package com.example.newsapp1.domain

import com.example.newsapp1.di.qualifier.ApiKey
import com.example.newsapp1.domain.network.NewsService
import com.example.newsapp1.domain.network.models.SourcesResponse
import com.example.newsapp1.domain.network.models.TopHeadlinesResponse
import javax.inject.Inject

class SourcesRepository
@Inject constructor(
    private val newsService: NewsService,
    @ApiKey private val apiKey: String
) {

    suspend fun getSources(): SourcesResponse {
        return newsService.getSources(apiKey)
    }

    suspend fun getNewTopHeaders(source: String, nextPage: Int): TopHeadlinesResponse {
        return newsService.getNewTopHeadlines(
            source = source,
            apiKey = apiKey,
            page = nextPage
        )
    }
}