package com.example.newsapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.MockData
import com.example.newsapp.NewsData
import com.example.newsapp.R
import com.example.newsapp.models.TopNewsArticle
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun TopNews(navController: NavController, articles: List<TopNewsArticle>) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Top News", fontWeight = FontWeight.SemiBold)
        LazyColumn {
            items(articles.size) {
                index ->
                TopNewsItem(
                    article = articles[index],
                    onNewsClicked = { navController.navigate("Detail/$index") }
                )

            }
        }
    }
}

@Composable
fun TopNewsItem(article: TopNewsArticle, onNewsClicked: () -> Unit = {}) {
    Box(modifier = Modifier
        .height(200.dp)
        .padding(8.0.dp)
        .clickable {
            onNewsClicked()
        }
    ) {
        CoilImage(
            imageModel = article.urlToImage,
            contentScale = ContentScale.Crop,
            error = ImageBitmap.imageResource(R.drawable.breaking_news),
            placeHolder = ImageBitmap.imageResource(R.drawable.breaking_news)
        )
        Column(modifier = Modifier
            .wrapContentHeight()
            .padding(top = 16.dp, start = 16.dp),
             verticalArrangement = Arrangement.SpaceBetween) {
            Text(text = article.publishedAt!!,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(80.dp))
            Text(text = article.title!!,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun TopNewsPreview() {
    TopNewsItem(TopNewsArticle(
        author = "Namita Singh",
        title = "Cleo Smith News - live: suspect in hospital",
        description = "the suspected kidnapper of people is caught",
        publishedAt = "2021-11-04T04:42:40Z")
    )
}