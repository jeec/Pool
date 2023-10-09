package com.jerry.mycompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetScaffoldSample() {
    val stateBot = rememberBottomSheetScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    BottomSheetScaffold(
        scaffoldState = stateBot,
        sheetContent = {
            Column(
                modifier = Modifier.fillMaxWidth().background(Color.Green),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "content")
                Spacer(modifier = Modifier.height(180.dp))
                Button(onClick = {
                    coroutineScope.launch {
                        stateBot.bottomSheetState.collapse()
                    }
                }) {
                    Text(text = "close")
                }
            }
        },
        topBar = {
            TopAppBar(title = {
                Text(text = "top app bar")
            })
        },
    ) {
        Text(text = "body")
    }
}

@Preview
@Composable
fun BottomSheetScaffoldSamplePreview() {
    BottomSheetScaffoldSample()
}