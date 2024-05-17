package com.jerry.mycompose

import android.util.Log
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SlideSample() {
    var progress by remember {
        mutableFloatStateOf(0f)
    }
    Slider(value = progress, onValueChange = {
        progress = it
    }, onValueChangeFinished = {
        Log.i(">>>", "reach slide maximum")
    }, valueRange = 0f..100f, steps = 20,
        colors = SliderDefaults.colors(
            activeTrackColor = Color.Red,
            inactiveTrackColor = Color.Green,
            thumbColor = Color.Yellow,
            disabledThumbColor = Color.White,
            inactiveTickColor = Color.Red

        )
    )
}

@Preview
@Composable
fun SlideSamplePreview() {
    SlideSample()
}