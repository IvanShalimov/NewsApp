package com.example.newsapp1.domain.network.models

import com.google.gson.annotations.SerializedName

data class TopHeadlinesResponse(
    @SerializedName("status")
    val status: ResponseStatus,
    @SerializedName("code")
    val errorCode: String?,
    @SerializedName("message")
    val errorMessage: String?,
    @SerializedName("totalResult")
    val totalResult: Int,
    @SerializedName("articles")
    val articles: List<Article>
)
