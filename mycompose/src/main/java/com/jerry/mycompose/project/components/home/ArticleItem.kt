package com.jerry.mycompose.project.components.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jerry.mycompose.project.entity.ArticleEntity

@Composable
fun ArticleItem(article: ArticleEntity) {
    Column(modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp)) {
        val annotatedString = buildAnnotatedString {
//            withStyle(
//                style = SpanStyle(
//                    color = Color.Red,
//                    background = Color.LightGray
//                )
//            ) {
//                append("label")
//            }
//
            pushStyle(
                style = SpanStyle(
                    color = Color.Red,
                    background = Color.LightGray
                )
            )
            append(article.label)
            pop()

            append(" ")
            append(article.title)
        }

        Text(
            text = annotatedString,
            fontFamily = FontFamily.Serif,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 2
        )

        Spacer(modifier = Modifier.size(10.dp))
        Surface(contentColor = Color.Gray) {
            Row() {
                Text(text = "source: " + article.source)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = article.time)
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Divider()
    }
}

@Preview(showSystemUi = true)
@Composable
fun ArticleItemPre() {
    ArticleItem(ArticleEntity("label", "title", "source", "time"))
}