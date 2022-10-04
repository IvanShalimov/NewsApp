package com.example.newsapp1.domain.network.models

import com.google.gson.annotations.SerializedName

data class SourcesResponse(
    @SerializedName("status")
    val status: ResponseStatus,
    @SerializedName("code")
    val errorCode: String?,
    @SerializedName("message")
    val errorMessage: String?,
    @SerializedName("sources")
    val sources: List<Source>
)
