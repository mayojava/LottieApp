package com.example.lottieapp.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.lottieapp.R
import com.example.lottieapp.data.local.BlogsEntity

@Composable
fun Blogs(blogs: List<BlogsEntity>) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = stringResource(id = R.string.latest_posts),
            style = MaterialTheme.typography.subtitle1
        )

        LazyRow(modifier = Modifier.padding(end = 16.dp)) {
            items(blogs) { blog ->
                BlogItem(blog = blog, modifier = Modifier.padding(start = 16.dp, bottom = 16.dp))
            }
        }

    }
}

@Composable
fun BlogItem(
    blog: BlogsEntity,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.size(280.dp, 240.dp)) {
        Column {
            NetworkImage(
                url = blog.imageUrl,
                contentDescription = "",
                modifier = Modifier
                    .height(140.dp)
                    .fillMaxWidth()
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = blog.title,
                    style = MaterialTheme.typography.h6,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = blog.postedAt.split("T").first(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.body2
                )
            }

        }
    }
}