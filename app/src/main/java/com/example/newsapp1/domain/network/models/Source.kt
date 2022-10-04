package com.example.newsapp1.domain.network.models

import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("url")
    val url: String
)
