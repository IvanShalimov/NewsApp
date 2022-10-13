package com.example.newsapp1.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.newsapp1.R
import com.example.newsapp1.presentation.ui.theme.NewsApp1Theme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.newsapp1.domain.SourcesRepository
import com.example.newsapp1.domain.network.models.SourcesResponse
import com.example.newsapp1.presentation.mapper.SourcesItemMapper
import com.example.newsapp1.presentation.mapper.TopHeadlinesMapper
import com.example.newsapp1.presentation.models.SourceItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsActivity : ComponentActivity() {


    private val sourceMapper: SourcesItemMapper by lazy { SourcesItemMapper() }
    //private val topHeadlinesMapper by lazy { TopHeadlinesMapper() }

    val sourcesRepository = SourcesRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var sources by rememberSaveable { mutableStateOf(listOf<SourceItem>())}
            requestSources { s -> sources = s }
            NewsApp1Theme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    NewsSourcesList(sources)
                }
            }
        }
    }

    private fun requestSources(callback: (s: List<SourceItem>) -> Unit) {
        sourcesRepository.getSources(object : Callback<SourcesResponse> {
            override fun onResponse(call: Call<SourcesResponse>, response: Response<SourcesResponse>) {
                callback(sourceMapper.map(response.body() as SourcesResponse))
            }

            override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                Log.d("test","${t.message}")
            }
        })
    }

    @Composable
    fun NewsSourcesList(sources:  List<SourceItem>) {
        LazyColumn {
            items(sources) {source ->
                Card(
                    backgroundColor = MaterialTheme.colors.primary,
                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
                        .clickable {
                            //TODO handle click
                        }
                ) {
                    MessageCard(source)
                }
            }
        }
    }

    @Composable
    fun MessageCard(source: SourceItem) {
        Row( modifier = Modifier.padding(8.dp)) {
            Image(
                painter = painterResource(id = R.drawable.profile_picture),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colors.primary, CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))

            var isExpanded by remember { mutableStateOf(false) }
            val surfaceColor by animateColorAsState(
                if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
            )


            Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
                Text(
                    source.name,
                    color = MaterialTheme.colors.secondary,
                    style = MaterialTheme.typography.h6
                )
                Spacer(modifier = Modifier.width(4.dp))

                Surface(
                    shape = MaterialTheme.shapes.medium,
                    color = surfaceColor,
                    modifier = Modifier
                        .animateContentSize()
                        .padding(1.dp)) {
                    Text(
                        source.description,
                        modifier = Modifier.padding(all = 4.dp),
                        maxLines = if(isExpanded) Int.MAX_VALUE else 1,
                        style = MaterialTheme.typography.body1
                    )
                }
                Text(source.url)
            }

        }

    }
}
