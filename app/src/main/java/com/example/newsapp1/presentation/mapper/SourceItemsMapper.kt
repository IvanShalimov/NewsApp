package com.example.newsapp1.presentation.mapper

import com.example.newsapp1.domain.network.models.SourcesResponse
import com.example.newsapp1.presentation.models.SourceItem

class SourcesItemMapper{

    fun map(response: SourcesResponse): List<SourceItem> {
        return response.sources.map {
            SourceItem(
                id = it.id,
                name =  it.name,
                description = it.description,
                url = it.url
            )
        }
    }
}