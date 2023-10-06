package com.jerry.mycompose

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListItemSample() {
    val data = remember {
        mutableStateListOf(MyData("sanEr"), MyData("siEr"))
    }

    Column {
        //test commit history
        //test again
        //test 2 again
        //test 3 again
        repeat(19) {
            ListItem(headlineText = {
                Text(text = "headLineText11")
            }, overlineText = {
                Text(text = "overLineText22")
            }, supportingText = {
                Text(text = "supportingText")
            }, leadingContent = {
                Icon(imageVector = Icons.Default.Person, contentDescription = null)
//        Text(text = "leadingContent")
            }, trailingContent = {
                Icon(imageVector = Icons.Default.Close, contentDescription = null)
//        Text(text = "trailingContent")
            }, colors = ListItemDefaults.colors(trailingIconColor = Color.Red, headlineColor = Color.Green))
        }
    }

}

data class MyData(val title: String, val name: String = "asdf")

@Preview
@Composable
fun ListItemSamplePreview() {
    ListItemSample()
}

//do somethings
//1 2
//再引出一个分支
//
