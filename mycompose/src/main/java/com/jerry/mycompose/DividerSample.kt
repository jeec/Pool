package com.jerry.mycompose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DividerSample() {
    Column {
        Divider(thickness = 20.dp, color = Color.Yellow)
        Divider(thickness = 10.dp, color = Color.Red)
        Divider(color = Color.Green, thickness = 50.dp, modifier = Modifier.padding(start = 10.dp))
    }
}

@Preview
@Composable
fun DividerSamplePreview() {
    DividerSample()
}