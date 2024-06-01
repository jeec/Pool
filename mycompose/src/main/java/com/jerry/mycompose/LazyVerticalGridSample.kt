package com.jerry.mycompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LazyVerticalGridSample() {
    LazyVerticalGrid(columns = GridCells.Fixed(4),
        content = {
            items(7) {
                Card() {
                    Text(
                        text = "asdf",
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(vertical = 40.dp)
                    )
                }
            }
        })
}

@Preview
@Composable
fun LazyVerticalGridSamplePreview() {
    LazyVerticalGridSample()
}