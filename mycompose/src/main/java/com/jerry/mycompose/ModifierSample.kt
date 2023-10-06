package com.jerry.mycompose

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ModifierSample() {
    Text(
        text = "welcome guys!",
        modifier = Modifier
            .background(Color.Yellow)
            .border(2.dp, Color.Red)
            .padding(10.dp)
            .clickable {
                Log.i(">>>", "click me")
            }
    )
}

@Preview
@Composable
fun ModifierSamplePreview() {
    ModifierSample()
}