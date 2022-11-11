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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.MockData
import com.example.newsapp.NewsData
import com.example.newsapp.R

@Composable
fun DetailScreen(newsData: NewsData, scrollState: ScrollState, navController: NavController) {

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
            Image(painter = painterResource(id = newsData.image),
                contentDescription = "image"
            )
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                InfoWithIcon(icon = Icons.Default.Edit, info = newsData.author)
                InfoWithIcon(icon = Icons.Default.DateRange, info = newsData.publishedAt)
            }
            Text(text = newsData.title, fontWeight = FontWeight.Bold)
            Text(text = newsData.description,
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
        newsData = MockData.topNewsList[1],
        rememberScrollState(),
        rememberNavController()
    )
}