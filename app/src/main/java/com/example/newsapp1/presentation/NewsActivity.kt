package com.example.newsapp1.presentation

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp1.R
import com.example.newsapp1.presentation.ui.theme.NewsApp1Theme

class NewsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsApp1Theme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MessageCard(Message("Android", "Jetpack Compose"))
                }
            }
        }
    }

    @Composable
    fun MessageCard(message: Message) {
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
            Column {
                Text(
                    message.name,
                    color = MaterialTheme.colors.secondary,
                    style = MaterialTheme.typography.h6
                )
                Spacer(modifier = Modifier.width(4.dp))

                Surface(shape = MaterialTheme.shapes.medium) {
                    Text(
                        message.body,
                        modifier = Modifier.padding(4.dp),
                        style = MaterialTheme.typography.body1
                    )
                }
            }

        }

    }

    @Preview(name = "Light Mode")
    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "Dark Mode"
    )
    @Composable
    fun MessageCardPreview() {
        NewsApp1Theme {
            Surface(modifier = Modifier.fillMaxSize()) {
                MessageCard(Message("Android", "Jetpack Compose"))
            }
        }
    }
}

data class Message(val name: String, val body: String)