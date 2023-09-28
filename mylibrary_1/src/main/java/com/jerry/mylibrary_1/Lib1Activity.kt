package com.jerry.mylibrary_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception

class Lib1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lib1)
        findViewById<Button>(R.id.button).setOnClickListener {
            val clazz = Class.forName("com.jerry.mylibrary_2.Lib2Activity")
            startActivity(Intent(this@Lib1Activity, clazz))
        }


//        repeat(1000000){
//            try {
//                //Thread.sleep(1000)
//            }catch (e: Exception) {
//
//            }
//            CoroutineScope(Dispatchers.IO).launch {
//                delay(2000)
//                Log.i(">>>", "asdf")
//            }
//        }
    }
}