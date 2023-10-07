package com.jerry.mycompose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

@Composable
fun DropDownMenuSample() {
    var bExpand by remember {
        mutableStateOf(false)
    }

    Column() {
        Button(onClick = {
            bExpand = true
        }) {
            Text(text = "click me to show DropDownMenu")
        }
        
        Spacer(modifier = Modifier.weight(1f).background(Color.Blue).width(10.dp))

        Button(onClick = {
            bExpand = true
        }) {
            Text(text = "click me to show DropDownMenu")
        }
        
        DropdownMenu(
            expanded = bExpand,
            offset = DpOffset(20.dp, 5.dp),
            onDismissRequest = {
                bExpand = false
            },
        ) {
            DropdownMenuItem(text = {
                Text(text = "drop down item 111")
            }, onClick = {
                bExpand = false
            })
            DropdownMenuItem(text = {
                Text(text = "drop down item 222")
            }, onClick = {
                bExpand = false
            })
            DropdownMenuItem(text = {
                Text(text = "drop down item 333")
            }, onClick = {
                bExpand = false
            })
        }


    }

}

@Preview
@Composable
fun DropDownMenuSamplePreview() {
    DropDownMenuSample()
}