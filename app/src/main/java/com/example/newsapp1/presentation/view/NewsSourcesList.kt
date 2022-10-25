package com.example.newsapp1.presentation.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp1.presentation.models.SourceItem

@ExperimentalUnitApi
@Composable
fun NewsSourcesList(sources: List<SourceItem>, onItemClicked: (item: SourceItem) -> Unit) {
    LazyColumn {
        items(sources) { source ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .clickable{ onItemClicked(source) },
                elevation = 10.dp
            ) {
                SourceCard(source)
            }
        }
    }
}

@ExperimentalUnitApi
@Composable
fun SourceCard(source: SourceItem) {
    Column(Modifier.padding(16.dp)) {
        Text(
            source.name,
            color = MaterialTheme.colors.secondary,
            style = MaterialTheme.typography.h6
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "Source: ${source.url}",
            style = MaterialTheme.typography.subtitle2,
            color = Color.Gray,
            fontSize = TextUnit(10f, TextUnitType.Sp),
            fontStyle = FontStyle.Italic
        )
        Spacer(modifier = Modifier.width(12.dp))
        Surface(
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.padding(1.dp)
        ) {
            Text(
                text = AnnotatedString(
                    text = source.description,
                    ParagraphStyle(textIndent = TextIndent(firstLine = 12.sp)
                    )
                ),
                modifier = Modifier.padding(all = 4.dp),
                style = MaterialTheme.typography.body1,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }

    }
}

@ExperimentalUnitApi
@Preview
@Composable
fun NewSourceListPreview() {
    val sources = listOf(
        SourceItem(
            id = "ABC News",
            name = "ABC Shocking News",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
            url = "https://www.lipsum.com"
        )
    )
    LazyColumn {
        items(sources) { source ->
            Card(
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 8.dp)
                    .clickable { }
            ) {
                SourceCard(source)
            }
        }
    }
}