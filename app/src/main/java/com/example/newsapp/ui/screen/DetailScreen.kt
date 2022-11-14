package com.example.newsapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
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
fun DetailScreen(article: TopNewsArticle, scrollState: ScrollState, navController: NavController) {

    Scaffold(
        topBar = {
            DetailTopAppBar(onBackPressed = { navController.popBackStack() })
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Detail Screen", fontWeight = FontWeight.SemiBold)

            CoilImage(
                imageModel = article.urlToImage,
                contentScale = ContentScale.Crop,
                error = ImageBitmap.imageResource(R.drawable.breaking_news),
                placeHolder = ImageBitmap.imageResource(R.drawable.breaking_news)
            )

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                InfoWithIcon(icon = Icons.Default.Edit, info = article.author?:"Not Available")
                InfoWithIcon(icon = Icons.Default.DateRange, info = article.publishedAt!!)
            }
            Text(text = article.title?:"Not Available", fontWeight = FontWeight.Bold)
            Text(text = article.description?:"Not Available",
                modifier = Modifier.padding(top = 16.dp)
            )

        }
    }
}

@Composable
fun InfoWithIcon(icon: ImageVector, info: String) {
    Row {
        Icon(
            imageVector = icon,
            contentDescription = "Author",
            modifier = Modifier.padding(end = 8.dp),
            colorResource(id = R.color.purple_500)
        )
        Text(text = info)
    }
}

@Composable
fun DetailTopAppBar(onBackPressed: () -> Unit = {}) {
    TopAppBar(
        title = { AppBarTitle() },
        navigationIcon = { AppBarNavIcon(onBackPressed) }
    )
}

@Composable
fun AppBarTitle() {
    Text(text = "Detail Screen", fontWeight = FontWeight.SemiBold)
}

@Composable
fun AppBarNavIcon(onBackPressed: () -> Unit = {}) {
    IconButton(onClick = { onBackPressed() }) {
        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    DetailScreen(
        TopNewsArticle(
            author = "Bob Smith",
            title = "Super Catchy Clickbait Title",
            description = "You should seen it... if you had been there, you would have done the same. He had it comi'. He only had himself to blame",
            publishedAt = "2021-11-04T04:42:40Z"
        ),
        rememberScrollState(),
        rememberNavController()
    )
}