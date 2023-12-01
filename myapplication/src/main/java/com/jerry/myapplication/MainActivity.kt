package com.jerry.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import com.jerry.myapplication.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.ThreadFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var myTest: MyTest
    @Inject lateinit var myInterface: MyInterface
    @Inject lateinit var myTestInterface: MyTestInterface
    @Inject lateinit var myTestString: MyTestString

    @StringType_A
    @Inject lateinit var stringA: String
    @StringType_B
    @Inject lateinit var stringB: String

    @Inject lateinit var injectedStr: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
//        myTest.print()
//        myTestInterface.print(myInterface)
//        myInterface.print()
//        myTestString.print()

        Log.i(">>>", "stringA: $stringA,  stringB: $stringB")
        Log.i(">>>", injectedStr)


        val scheduledPool = Executors.newScheduledThreadPool(3)
//        {
//            Thread {
//                Log.w(TAG, "newThread: ")
//            }
//        }
//        val fixedPool = Executors.newFixedThreadPool(10)

//        repeat(10) {
//            scheduledPool.scheduleAtFixedRate({
//                Log.i(TAG, "onCreate$it: ${Thread.currentThread().name}")
//            }, 1, 1, TimeUnit.SECONDS)
//        }
    //        fixedPool.submit()

    }

    companion object {
        private const val TAG = ">>>"
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}