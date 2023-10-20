package com.jerry.mycompose.project

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomePage() {
    Text(text = "home page")
}

@Preview
@Composable
fun HomePagePreview() {
    HomePage()
}