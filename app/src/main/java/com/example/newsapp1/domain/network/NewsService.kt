package com.example.newsapp1.domain.network

import com.example.newsapp1.domain.network.models.SourcesResponse
import com.example.newsapp1.domain.network.models.TopHeadlinesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("v2/top-headlines/sources")
    fun getSources(@Query("apiKey") apiKey: String): Call<SourcesResponse>

    @GET("v2/top-headlines")
    fun getTopHeadlines(
        @Query("sources") source: String,
        @Query("apiKey") apiKey: String
    ): Call<TopHeadlinesResponse>
}