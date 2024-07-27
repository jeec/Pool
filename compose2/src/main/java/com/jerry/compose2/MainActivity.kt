package com.jerry.compose2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import com.jerry.compose2.ui.theme.MyApplicationTheme
import kotlinx.coroutines.delay

private const val TAG = ">>>"

class MainActivity : ComponentActivity() {

    var a: String = "aaa"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier.background(Color.Red)) {
    val pagerState = rememberPagerState {
        4
    }

    val focusRequesters = remember { List(4) { FocusRequester() } } // 假设有3个页面
    val focusManager = LocalFocusManager.current


    val focusRequesters1 = remember {
        List(3) {
            List(2) {
                FocusRequester()
            }
        }
    } // 假设每个页面有2个元素

    SideEffect {
        Log.d(">>>", "Greeting: 111")
    }

    LaunchedEffect(pagerState.currentPage) {
        Log.d(">>>", "aaaaa")
        delay(500)
//        focusManager.clearFocus()
        focusRequesters[pagerState.currentPage].requestFocus()
    }
    HorizontalPager(state = pagerState) { pageIndex ->

        Text(
            text = "$pageIndex",
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequesters[pageIndex])
                .focusable()
        )
    }
}

@Composable
fun MyText(modifier: Modifier = Modifier) {
    Text(text = "text2", modifier = modifier)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}