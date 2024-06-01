package com.jerry.mycompose

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun CompositionSample() {
    val navController = rememberNavController()

    val user = User("every page can see me")

    CompositionLocalProvider(LocalActiveUser provides user) {
        NavHost(navController = navController, startDestination = "Host") {
            composable("Host") {
                HomePage {
                    navController.navigate("Setting")
                }
            }
            composable("Setting") {
                SettingPage()
            }
        }
    }
}

@Composable
fun HomePage(onTab: ()->Unit) {
    Button(onClick = onTab) {
        Text(text = "首页，点击去下一页${LocalActiveUser.current.name}")
    }
}

@Composable
fun SettingPage() {
    Text(text = "settingPage ${LocalActiveUser.current.name}")
}

val LocalActiveUser = compositionLocalOf<User> {
    User("default")
}

data class User(val name: String)

@Preview
@Composable
fun CompositionSamplePrev() {
    CompositionSample()
}