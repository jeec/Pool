package com.jerry.mycompose

import android.util.Log
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SwitchSample() {
    var state by remember {
        mutableStateOf(true)
    }
    Switch(
        checked = state,
        onCheckedChange = {
            state = it
        },
        colors = SwitchDefaults.colors(
            checkedBorderColor = Color.Red,
            uncheckedBorderColor = Color.Black,
            checkedThumbColor = Color.Yellow,
            uncheckedThumbColor = Color.Black,
            checkedTrackColor = Color.Blue,
            uncheckedTrackColor = Color.Green
        ),
        thumbContent = {
            //Log.i(">>>", "asdf")
        }
    )
}

@Preview
@Composable
fun SwitchSamplePreview() {
    SwitchSample()
}