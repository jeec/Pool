package com.jerry.mycompose

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun StateSample() {
    var count by remember {
        mutableStateOf(1)
    }

    Log.i(">>>", "111 ${count}")

    Text(text = "text${count}", modifier = Modifier
        .padding(20.dp)
        .clickable {
            ++count
            Log.i(">>> ", "${count}")
        })

}

@Preview
@Composable
fun StateSamplePreview() {
    StateSample()
}