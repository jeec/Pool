package com.jerry.mycompose

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

private const val TAG = ">>>"

@Composable
fun LifecycleSample() {
    var count by remember {
        mutableIntStateOf(0)
    }
    Log.i(TAG, "LifecycleSample: 刷新compose方法")

    LaunchedEffect(Unit) {
        //启动时只执行一次
        Log.i(TAG, "LifecycleSample: 启动时只执行一次")
    }
    LaunchedEffect(count) {
        //只要count变化，则会执行
        Log.i(TAG, "LifecycleSample: 只要count变化，则会执行")
    }


    Column {
//        Text(text = "text: count: $count")

        TextButton(onClick = {
            count++
        }) {
            Text(text = "textBtn, 点我加1：$count")
        }

        Button(onClick = {
            Log.i(TAG, "LifecycleSample: >>>")
        }) {
            Text(text = "commonBtn")
        }
        if (count == 3) {
            SubLayout_disposableComposable()
        }
    }

}

@Composable
fun SubLayout_disposableComposable() {
//    DisposableEffect(Unit) {
//        Log.i(TAG, "SubLayout_disposableComposable: 创建了disposable composable")
//        onDispose {
//            Log.i(TAG, "SubLayout_disposableComposable: 销毁了disposable composable")
//        }
//    }

    Text(text = "subLayout_DisposableEffect")
    LifecycleObserverSample()
}

@Composable
fun LifecycleObserverSample() {
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(Unit) {

        Log.i(TAG, "LifecycleObserverSample: 11111")
        val lifecycleEventObserver = LifecycleEventObserver { source, event ->
            when(event) {
                Lifecycle.Event.ON_CREATE ->{
                    Log.i(TAG, "onStateChanged: ON_CREATE")
                }
                Lifecycle.Event.ON_START -> {
                    Log.i(TAG, "onStateChanged: ON_START")
                }
                Lifecycle.Event.ON_RESUME -> {
                    Log.i(TAG, "onStateChanged: ON_RESUME")
                }
                Lifecycle.Event.ON_PAUSE -> {
                    Log.i(TAG, "onStateChanged: ON_PAUSE")
                }
                Lifecycle.Event.ON_STOP -> {
                    Log.i(TAG, "onStateChanged: ON_STOP")
                }
                Lifecycle.Event.ON_DESTROY -> {
                    Log.i(TAG, "onStateChanged: ON_DESTROY")
                }
                Lifecycle.Event.ON_ANY -> {
                    Log.i(TAG, "onStateChanged: ON_ANY")
                }
            }
        }

        lifecycleOwner.lifecycle.addObserver(lifecycleEventObserver)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(lifecycleEventObserver)
        }
    }
}

@Preview
@Composable
fun LifecyclePreview() {
    LifecycleSample()
}