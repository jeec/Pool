package com.jerry.myapplication

import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
//    @Test
//    fun useAppContext() {
//        // Context of the app under test.
//        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
//        assertEquals("com.jerry.myapplication", appContext.packageName)
//    }
//    fun makeFlow() = flow {
//        println("sending first value")
//        emit(1)
//        println("first value collected, sending another value")
//        emit(2)
//        println("second value collected, sending a third value")
//        emit(3)
//        println("done")
//    }
//
//    @Test
//    fun test() {
//        CoroutineScope(Dispatchers.IO).launch {
//            val myFlow = makeFlow().take(2)
//            myFlow.collect{
//                println(">>>111 $it")
//            }
//            myFlow.collect{
//                println(">>>222 $it")
//            }
//            println("flow is completed")
//        }
//    }

    private val result:Flow<String>
        get()= aFlow().combine(bFlow()) { a, b->
            println("a: $a, b: $b")
            "str,result"
        }

    @Test
    fun testCombine(): Unit {
        CoroutineScope(Dispatchers.IO).launch {
            result.collect {
                println(">>> result: $it")
            }
        }
    }

    @OptIn(FlowPreview::class)
    fun aFlow() = MyInnerClass()::m1.asFlow()
        .onStart {
            Log.i("jc", "onStart")
        }

    @OptIn(FlowPreview::class)
    fun bFlow() = MyInnerClass()::m2.asFlow()

    inner class MyInnerClass {
        fun m1() {
            println(">>> m1")
        }

        fun m2(): String {
            println(">>> m2")
            return "222"
        }
    }



}

