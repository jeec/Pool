package com.jerry.mycompose.project.components.study

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HeadPart() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        Text(text = "Daily Task")
        Text(text = "study period")

    }
}

@Preview(showSystemUi = true)
@Composable
fun HeadPartPrev() {
    HeadPart()
}