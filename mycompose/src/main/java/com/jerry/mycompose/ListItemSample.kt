package com.jerry.mycompose

import android.util.Log
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListItemSample() {
    var data = remember {
        mutableStateListOf(
            MyData("sanEr", true),
            MyData("sanEr", false),
            MyData("sanEr", false),
            MyData("sanEr", false),
            MyData("sanEr", true),
            MyData("sanEr", false),
            MyData("sanEr", false),
            MyData("sanEr", false),
            MyData("sanEr", true),
            MyData("sanEr", false),
            MyData("sanEr", false),
            MyData("sanEr", false),
        )
    }
    Log.i(">>>", "start")
    val scroller = rememberScrollState()
    Column (
        //加上此项可以上下滑动
        modifier = Modifier.verticalScroll(scroller)
    ){
        repeat(data.size) {index ->
            ListItem(
                headlineText = {
                    Text(text = "headLineText11")
                },
                overlineText = {
                    Text(text = "overLineText22")
                },
                supportingText = {
                    Text(text = "supportingText")
                },
                leadingContent = {
                    Checkbox(checked = data[index].bChecked, onCheckedChange = { bChecked ->
                        Log.i(">>>", "$bChecked")
                        data[index].bChecked = bChecked
                        //todo 此处只改变内部的MyData，并未通知到data,所以点击页面CheckBox无反映
                    })
                },
                trailingContent = {
                    Icon(imageVector = Icons.Default.Close, contentDescription = null)
                },
                colors = ListItemDefaults.colors(
                    trailingIconColor = Color.Red,
                    headlineColor = Color.Green
                )
            )

            //检查创建和销毁生命周期
            DisposableEffect(Unit) {
                Log.i(">>>", "111_disposableEffect")
                onDispose {
                    Log.i(">>>", "222_onDispose")
                }
            }
        }
    }
}

data class MyData(val title: String, var bChecked: Boolean = false)

@Preview
@Composable
fun ListItemSamplePreview() {
    ListItemSample()
}

//do somethings
//1 2
//再引出一个分支
//
