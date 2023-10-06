package com.jerry.mycompose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CardSample() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.Blue,
            contentColor = Color.Green
        ),
        border = BorderStroke(2.dp, color = Color.Yellow),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Text(text = "asdfasdfasdf", modifier = Modifier.padding(top = 10.dp))
        Text(text = "222222222222",
            Modifier
                .background(Color.Red)
                .padding(horizontal = 10.dp))
        Text(text = "33333333333")
    }
}

@Preview(showSystemUi = false)
@Composable
fun CardSamplePreview() {
    CardSample()
}