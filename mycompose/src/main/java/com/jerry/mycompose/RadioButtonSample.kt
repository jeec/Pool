package com.jerry.mycompose

import android.util.Log
import android.widget.RadioButton
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RadioButtonSample() {

    var mSelected = remember {
//        mutableStateOf("")
        mutableStateListOf<Boolean>(false, false)
    }

    Column {
        mSelected.forEachIndexed { i, b ->
            RadioButton(selected = b, onClick = {
                repeat(mSelected.size) { j ->
                    mSelected[j] = i == j
                }
//                mSelected[index] = true
            })
        }
    }

}

@Preview
@Composable
fun RadioButtonSamplePreview() {
    RadioButtonSample()
}