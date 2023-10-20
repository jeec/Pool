package com.jerry.mycompose.project

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun FunNavHost(navController: NavHostController, modifier: Modifier) {
    NavHost(navController = navController, startDestination = "Home", modifier = modifier) {
        composable("Home") {
            HomePage()
        }
        composable("Mine") {
            MinePage()
        }
    }
}