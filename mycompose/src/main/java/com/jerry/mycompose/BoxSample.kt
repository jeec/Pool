package com.jerry.mycompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BoxSample() {
    Box(modifier = Modifier.size(200.dp).background(Color.Green)) {
        Box(modifier = Modifier.size(200.dp).background(Color.Red).align(Alignment.Center))
        Box(modifier = Modifier.size(100.dp).background(Color.Blue))
    }
}

@Preview(showSystemUi = true)
@Composable
fun BoxSamplePreview() {
    BoxSample()
}