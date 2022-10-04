package com.example.newsapp1.domain.network.models

import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("author")
    val author: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("urlToImage")
    val thumbnail: String,
    @SerializedName("publishedAt")
    val publishedAt: String
)
