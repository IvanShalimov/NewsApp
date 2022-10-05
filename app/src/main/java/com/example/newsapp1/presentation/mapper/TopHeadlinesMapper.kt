package com.example.newsapp1.presentation.mapper

import com.example.newsapp1.domain.network.models.TopHeadlinesResponse
import com.example.newsapp1.presentation.models.ArticleItem

class TopHeadlinesMapper {

    fun map(response: TopHeadlinesResponse): List<ArticleItem> {
        return response.articles.map {
            ArticleItem(
                author = it.author,
                title = it.title,
                description = it.description,
                thumbnail = it.thumbnail,
                publishedAt = it.publishedAt
            )
        }
    }
}