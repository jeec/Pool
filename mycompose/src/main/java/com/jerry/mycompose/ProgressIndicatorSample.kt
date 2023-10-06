package com.jerry.mycompose

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProgressIndicatorSample() {
//    CircularProgressIndicator(progress = 0.6f, color = Color.Red, strokeWidth = 6.dp)
    LinearProgressIndicator(color = Color.Red, trackColor = Color.Green)
}

@Preview
@Composable
fun ProgressIndicatorSamplePreview() {
    ProgressIndicatorSample()
}