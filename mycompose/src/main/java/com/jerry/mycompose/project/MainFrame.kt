package com.jerry.mycompose.project

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainFrame() {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val currentScreen = fanTabScreens.find { it.route == currentDestination?.route } ?: Main

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(snackbarHost = {
        SnackbarHost(hostState = snackbarHostState)
    }, bottomBar = {
        BottomNavigation(backgroundColor = MaterialTheme.colorScheme.surface) {
            fanTabScreens.forEachIndexed { index, screen ->
                BottomNavigationItem(
                    selected = currentScreen == screen,
                    onClick = {
                        navController.goPageSingleTop(screen.route)
                    },
                    icon = {
                        Icon(
                            imageVector = screen.icon,
                            contentDescription = null,
                        )
                    },
                    label = {
                        Text(text = screen.route)
                    },
                    alwaysShowLabel = true,
                    selectedContentColor = MaterialTheme.colorScheme.primary,
                    unselectedContentColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
                )
            }
        }
    }) {
        FunNavHost(navController, modifier = Modifier.padding(it))
        LaunchedEffect(key1 = Unit, block = {
            delay(1000)
            when (snackbarHostState.showSnackbar("hello", "action", true)) {
                SnackbarResult.ActionPerformed -> {
                    Log.i(">>>", "action_performed")
                }

                SnackbarResult.Dismissed -> {
                    Log.i(">>>", "dismissed")
                }
            }
        })
    }
}

fun NavHostController.goPageSingleTop(route: String) {
    navigate(route) {
        launchSingleTop = true
//        restoreState = true
    }
}

@Preview
@Composable
fun MainFramePreview() {
    MainFrame()
}

