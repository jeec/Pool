package com.jerry.mycompose.navi

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.jerry.mycompose.project.MainFrame

class NavigatorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            MyApp()
            MainFrame()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            Row {
                TextButton(onClick = {
                    navController.goPage(ROUTE_1)
                }) {
                    Text(text = "tab1")
                }
                TextButton(onClick = {
                    navController.goPage(ROUTE_2)
                }) {
                    Text(text = "tab2")
                }
                TextButton(onClick = {
                    navController.goPage("$ROUTE_3/args from clickTabBtn")
                }) {
                    Text(text = "tab3")
                }
            }
        }
    ) { it ->
        RallyNavHost(
            navController = navController,
            modifier = Modifier.padding(it)
        )
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