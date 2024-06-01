package com.jerry.mycompose.project

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jerry.mycompose.project.screens.home.HomePage
import com.jerry.mycompose.project.screens.home.MinePage
import com.jerry.mycompose.project.screens.home.StudyPage

@Composable
fun FunNavHost(navController: NavHostController, modifier: Modifier) {
    NavHost(navController = navController, startDestination = Mine.route, modifier = modifier) {
        composable(Main.route) {
            HomePage()
        }
        composable(Study.route) {
            StudyPage()
        }
        composable(Mine.route) {
            MinePage()
        }
    }
}