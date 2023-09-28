package com.jerry.myapplication

import kotlinx.coroutines.delay
import org.junit.Test

import org.junit.Assert.*
import kotlin.concurrent.thread

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        println("asdf")
        val t1 = thread {
            println("111111")
        }
        val t2 = thread {
//            t1.join()
            println("222222")
        }

        var index = 0
        while (index <= 5) {
            index++
            t1.run()
            t2.run()

        }
        assertEquals(4, 2 + 2)
    }


}