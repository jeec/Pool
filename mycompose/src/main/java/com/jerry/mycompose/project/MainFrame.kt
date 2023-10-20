package com.jerry.mycompose.project

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jerry.mycompose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainFrame() {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val currentScreen = fanTabScreens.find { it.route == currentDestination?.route } ?: Main

    Scaffold(
        bottomBar = {
            BottomNavigation(backgroundColor = Color.White) {
                fanTabScreens.forEachIndexed { index, screen ->
                    BottomNavigationItem(selected = currentScreen == screen,
                        onClick = {
                            navController.goPageSingleTop(screen.route)
                        }, icon = {
                            Icon(
                                imageVector = screen.icon,
                                contentDescription = null,
                            )
                        }, label = {
                            Text(text = screen.route)
                        }, alwaysShowLabel = true,
                        selectedContentColor = colorResource(id = R.color.black),
                        unselectedContentColor = colorResource(id = R.color.black).copy(alpha = 0.3f)
                    )
                }
            }
        }
    ) {
        FunNavHost(navController, modifier = Modifier.padding(it))
    }
}

fun NavHostController.goPageSingleTop(route: String) {
    navigate(route) {
        launchSingleTop = true
        restoreState = true
    }
}

@Preview
@Composable
fun MainFramePreview() {
    MainFrame()
}