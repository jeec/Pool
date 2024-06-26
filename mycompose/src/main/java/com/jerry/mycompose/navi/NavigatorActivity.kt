package com.jerry.mycompose.navi

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.jerry.mycompose.project.MainFrame
import com.jerry.mycompose.ui.theme.MyApplicationTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NavigatorActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface {
//                    MainFrame()
                    Text(text = "asdf", modifier = Modifier.clickable {
                        finish()
                    })
                }
            }
        }


    }

    override fun onStart() {
        super.onStart()
        Log.d(">>>", "onStart: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(">>>", "onDestroy: ")
    }
}

fun NavHostController.goPage(route: String): Unit {
    navigate(route) {
        popUpTo(
            graph.findStartDestination().id
        ) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }
}

const val ROUTE_1 = "first_page"
const val ROUTE_2 = "second_page"
const val ROUTE_3 = "third_page"

const val param = "data"
const val data = "I_hate_you"