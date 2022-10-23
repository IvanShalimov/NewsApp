package com.example.newsapp1.presentation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp1.presentation.models.ArticleItem
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.flow.Flow

@Composable
fun TopHeaders(topHeadlines: Flow<PagingData<ArticleItem>>) {
    val headlines = topHeadlines.collectAsLazyPagingItems()
    LazyColumn {
        items(headlines.itemCount) { index ->
            headlines[index]?.let {
                HeadlineCard(it)
            }
        }
    }
}

@Composable
fun HeadlineCard(headline: ArticleItem) {
    Card(
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            GlideImage(
                imageModel = { headline.thumbnail },
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(headline.title)
                Text(headline.description)
                Text(headline.author)
                Text(headline.publishedAt)
            }
        }

    }
}