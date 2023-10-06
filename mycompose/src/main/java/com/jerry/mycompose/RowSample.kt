package com.jerry.mycompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RowSample() {
    Row {
        Text(text = "hello guys", modifier = Modifier
            .background(Color.Red)
            .fillMaxWidth(fraction = 0.5f))
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "girls hello")
    }
}

@Preview(showSystemUi = true)
@Composable
fun RowSamplePreview() {
    RowSample()
}