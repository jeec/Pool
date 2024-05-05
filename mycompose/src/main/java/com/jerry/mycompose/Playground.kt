package com.jerry.mycompose

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay

@Composable
fun Playground(
    vm: MyViewModel = viewModel(),
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {
    LaunchedEffect(key1 = Unit) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            vm.hotShareFlow.collect {
                Log.d(">>>", "receive... $it")
                delay(1000)
            }
        }
    }
}

@Composable
fun AButton() {
    DisposableEffect(key1 = Unit, effect = {
        Log.d(">>>", "888_Dispose_ABtn")
        onDispose {
            Log.d(">>>", "999_onDispose_ABtn")
        }
    })
    OutlinedButton(onClick = {

    }) {
        Text(text = "outLineBtn")
    }
}

@Preview(showSystemUi = true)
@Composable
fun PlaygroundPreview() {
    Playground()
}