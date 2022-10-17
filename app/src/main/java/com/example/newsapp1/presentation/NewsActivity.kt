package com.example.newsapp1.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.newsapp1.presentation.models.ArticleItem
import com.example.newsapp1.presentation.models.SourceItem
import com.example.newsapp1.presentation.ui.theme.NewsApp1Theme
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsActivity : ComponentActivity() {

    private val sourceViewModel: SourceViewModel by viewModels()
    private val topHeadlineViewModel: TopHeadlinesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var isDetail by rememberSaveable { mutableStateOf(false) }

            NewsApp1Theme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    if (isDetail) {
                        TopHeaders(topHeadlineViewModel.topHeadlines)
                    } else {
                        sourceViewModel.getSources()
                        NewsSourcesList(sourceViewModel.sources) { source ->
                            isDetail = true
                            topHeadlineViewModel.getTopHeadlines(source.id)
                        }
                    }
                }
                BackHandler {
                    isDetail = false
                }
            }
        }
    }

    @Composable
    fun NewsSourcesList(sources: List<SourceItem>, onItemClicked: (item: SourceItem) -> Unit) {
        LazyColumn {
            items(sources) { source ->
                Card(
                    modifier = Modifier
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                        .clickable { onItemClicked(source) }
                ) {
                    SourceCard(source)
                }
            }
        }
    }

    @Composable
    fun SourceCard(source: SourceItem) {
        Row(modifier = Modifier.padding(8.dp)) {
            Column {
                Text(
                    source.name,
                    color = MaterialTheme.colors.secondary,
                    style = MaterialTheme.typography.h6
                )
                Spacer(modifier = Modifier.width(4.dp))

                Surface(
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .animateContentSize()
                        .padding(1.dp)
                ) {
                    Text(
                        source.description,
                        modifier = Modifier.padding(all = 4.dp),
                        style = MaterialTheme.typography.body1
                    )
                }
                Text(source.url)
            }
        }
    }

    @Composable
    fun TopHeaders(articleItems: List<ArticleItem>) {
        LazyColumn {
            items(articleItems) { headline ->
                HeadlineCard(headline)
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
}
