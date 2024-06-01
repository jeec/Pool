package com.jerry.mycompose

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.concurrent.fixedRateTimer

class MyViewModel : ViewModel() {

    private val _myLiveData: MutableLiveData<String> = MutableLiveData()
    val myLiveData: LiveData<String> = _myLiveData

    val coldFlow = flow<Int> {
        var x = 0
        while (x<10) {
            emit(5)
            Log.d(">>>", "111 cold flow produce data... $x")
            delay(1000)
        }
    }

    private val _stateFlow: MutableStateFlow<Int> = MutableStateFlow(0)
    val hotStateFlow = _stateFlow.asStateFlow()

    private val _shareFlow: MutableSharedFlow<Int> = MutableSharedFlow(replay = 0, extraBufferCapacity = 10, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val hotShareFlow = _shareFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            delay(1000)
            repeat(10000) {
                _shareFlow.emit(it)
                Log.d(">>>", "send... $it")
            }
        }
    }

    val client: OkHttpClient = OkHttpClient()
    val url = "https://raw.github.com/square/okhttp/master/README.md"
    fun okhttpGet() {
        viewModelScope.launch(Dispatchers.IO) {
            val request = Request.Builder()
                .url(url)
                .build()
            val response = client.newCall(request).execute()
            val result = response.body?.string()
            Log.d(">>>", "okhttpGet: $result")
            _myLiveData.postValue(result)
        }
    }
}