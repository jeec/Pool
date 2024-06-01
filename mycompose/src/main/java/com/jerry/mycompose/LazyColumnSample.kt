package com.jerry.mycompose

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

private const val TAG = ">>>"

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun LazyColumnSample() {
    val data = listOf(
        "12", "34", "45", "78",
        "12", "34", "45", "78",
        "12", "34", "45", "78",
        "12", "34", "45", "78",
        "12", "34", "45", "78",
        "12", "34", "45", "78",
        "12", "34", "45", "78",
        "12", "34", "45", "78",
        "12", "34", "45", "78",
        "12", "34", "45", "78",
        "12", "34", "45", "78",
        "12", "34", "45", "78",
        "12", "34", "45", "78",
        "12", "34", "45", "78",
        "12", "34", "45", "78",
        "12", "34", "45", "78",
        "12", "34", "45", "78",
    )
    val coroutineScope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()
    LazyColumn(
        modifier = Modifier
            .background(Color.Green)
            .fillMaxWidth(),
        state = lazyListState,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            item {
                Text(text = "asdf", modifier = Modifier.background(Color.White))
            }
            stickyHeader { Text(text = "111sticky Header", modifier = Modifier.background(Color.Red)) }
            items(20) {
                Divider()
                ListItem(headlineText = {
                    Text(text = "headLineText")
                }, leadingContent = {
                    Icon(imageVector = Icons.Default.Person, contentDescription = null)
                }, modifier = Modifier.clickable {
                    coroutineScope.launch {
                        lazyListState.animateScrollBy(100f)
                    }
                })
            }
            stickyHeader { Text(text = "222sticky Header", modifier = Modifier.background(Color.Red)) }
            items(data) {
                Divider()
                Text(text = it)
                DisposableEffect(Unit) {
                    Log.i(TAG, "LazyColumnSample: onCreate")
                    onDispose {
                        Log.i(TAG, "LazyColumnSample: onDispose")
                    }
                }
            }
        })
}

@Preview(showSystemUi = true)
@Composable
fun LazyColumnSamplePreview() {
    LazyColumnSample()
}