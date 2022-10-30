package com.example.newsapp1.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp1.presentation.models.ArticleItem
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.flow.Flow

@ExperimentalUnitApi
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

@ExperimentalUnitApi
@Composable
fun HeadlineCard(headline: ArticleItem) {
    Card(
        modifier = Modifier.padding(vertical = 12.dp, horizontal = 8.dp),
        elevation = 8.dp
    ) {
        Column{
            Row(modifier = Modifier.padding(8.dp)){
                GlideImage(
                    imageModel = { headline.thumbnail },
                    modifier = Modifier.size(80.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = headline.title,
                        style = MaterialTheme.typography.h6
                    )
                    Text(
                        text = headline.author,
                        style = MaterialTheme.typography.subtitle2,
                        color = Color.Gray,
                        fontSize = TextUnit(10f, TextUnitType.Sp),
                        fontStyle = FontStyle.Italic
                    )
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = AnnotatedString(
                    text = headline.description,
                    ParagraphStyle(
                        textIndent = TextIndent(firstLine = 12.sp)
                    )
                )
            )
            Text(
                modifier = Modifier.fillMaxWidth().padding(4.dp),
                text = headline.publishedAt,
                style = MaterialTheme.typography.subtitle2,
                color = Color.Gray,
                fontSize = TextUnit(10f, TextUnitType.Sp),
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Right
            )
        }
    }
}