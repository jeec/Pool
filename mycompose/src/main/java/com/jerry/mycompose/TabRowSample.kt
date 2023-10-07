package com.jerry.mycompose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.LeadingIconTab
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TabRowSample() {
    var selectedTab by remember {
        mutableStateOf(0)
    }

    TabRow(selectedTabIndex = selectedTab,
        divider = {
            Divider(color = Color.Green)
        },
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier
                    .tabIndicatorOffset(tabPositions[selectedTab])
                    .border(20.dp, Color.Red, shape = RoundedCornerShape(15.dp)),
                height = 20.dp,
                color = Color.Transparent//将真实的背景色隐藏起来，用border调大，用作背景颜色。
            )
        }) {
        Tab(selected = selectedTab == 0,
            selectedContentColor = Color.Red,
            unselectedContentColor = Color.Gray, onClick = {
                selectedTab = 0
            }) {
            Text(text = "type 111")
        }
        Tab(selected = selectedTab == 1, text = {
            Text(text = "type 222")
        }, icon = {
            Icon(imageVector = Icons.Default.Person, contentDescription = null)
        }, onClick = {
            selectedTab = 1
        })
        LeadingIconTab(selected = selectedTab == 2,
            onClick = {
                selectedTab = 2
            },
            text = { Text(text = "type 333") },
            icon = {
                Icon(imageVector = Icons.Default.AccountBox, contentDescription = null)
            })
    }
}

@Preview(showSystemUi = true)
@Composable
fun TabRowSamplePreview() {
    TabRowSample()
}