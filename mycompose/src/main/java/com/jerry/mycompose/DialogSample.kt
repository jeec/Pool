package com.jerry.mycompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun DialogSample() {
    var bShowDialog by remember {
        mutableStateOf(false)
    }
    Column {
        Button(onClick = {
            bShowDialog = true
        }) {
            Text(text = "show Dialog")
        }

        if (bShowDialog) {
            Dialog(onDismissRequest = {
                bShowDialog = false
            }) {
                Surface(
                    color = Color.White,
                    modifier = Modifier
                        .width(300.dp)
                        .height(120.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text = "this is a dialog!")
                }
            }
        }
    }
}

@Preview
@Composable
fun DialogSamplePreview() {
    DialogSample()
}