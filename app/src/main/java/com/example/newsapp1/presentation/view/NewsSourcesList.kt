package com.example.newsapp1.presentation.view

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp1.presentation.models.SourceItem

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