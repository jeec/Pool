package com.jerry.mycompose

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CheckBoxSample() {
    val checkList = remember {
        mutableStateListOf(false, false)
    }
    Column {
        checkList.forEachIndexed { index, b ->
            Checkbox(checked = b, onCheckedChange = {
               repeat(checkList.size) {
                   checkList[it] = index == it
               }
            })
        }
    }
}

@Preview
@Composable
fun CheckBoxSamplePreview() {
    CheckBoxSample()
}