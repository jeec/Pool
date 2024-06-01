package com.jerry.mycompose

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BackDropScaffoldSample() {
    val scaffoldState = rememberBackdropScaffoldState(initialValue = BackdropValue.Concealed)
    val coroutineScope = rememberCoroutineScope()
    BackdropScaffold(scaffoldState = scaffoldState,
        appBar = {
            TopAppBar(title = {
                Text(text = "title")
            }, navigationIcon = {
                IconButton(onClick = {
                    coroutineScope.launch {
                        scaffoldState.reveal()
                    }
                }) {
                    if (scaffoldState.isConcealed) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = null
                        )
                    } else {
                        Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = null)
                    }
                }
            })
        }, backLayerContent = {
            LazyColumn(content = {
                items(20) {
                    ListItem {
                        Text(text = "background text")
                    }
                }
            })
        },
        frontLayerContent = {
            Text(text = "front")
        }) {

    }
}

@Preview
@Composable
fun BackDropScaffoldSamplePreview() {
    BackDropScaffoldSample()
}