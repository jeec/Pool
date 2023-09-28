package com.jerry.myapplication

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.jerry.annation_defination.MyAnnotation
import com.jerry.myapplication.databinding.ActivityMain2Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

@MyAnnotation
class MainActivity2 : AppCompatActivity() {

//    @TestAnnotation
//    val name: String = "wang"
//    @TestAnnotation
//    val age: Int = 12

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        testCombine()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    private val result: Flow<String>
        get()= aFlow().combine(cFlow()) { a, c->
            (1..99999).forEach {one ->
                (1..9999).forEach { two ->
                    val data = one.times(two)
                }
            }
           c
        }
        .combine(bFlow()) {cIndex, b->
            cIndex
        }.buffer(1000)

    fun testCombine(): Unit {
        CoroutineScope(Dispatchers.Default).launch {
            result.collect {
                delay(2000)
                println(">>> collect: $it")
            }
        }
    }

    @OptIn(FlowPreview::class)
    fun aFlow() = MyInnerClass()::m1.asFlow()


    @OptIn(FlowPreview::class)
    fun bFlow() = MyInnerClass()::m2.asFlow()

    @OptIn(FlowPreview::class)
    fun cFlow() = flow<String> {
        repeat(990000000) {
            println(">>> emit: $it")
            emit("$it")
        }
    }

    @SuppressWarnings
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