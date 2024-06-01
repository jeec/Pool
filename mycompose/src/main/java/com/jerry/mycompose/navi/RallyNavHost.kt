package com.jerry.mycompose.navi

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RallyNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = ROUTE_1,
        modifier = modifier
    ) {
        //PAGE 1
        composable(route = ROUTE_1) {
            Column(modifier = Modifier
                .background(Color.Red)
                .semantics {
                    contentDescription = "desc_page_1"//用来单元测试
                }) {
                Text(text = "first", fontSize = 63.sp, modifier = Modifier.semantics {
                    contentDescription = "desc_btn_page_1"
                }.clickable {
                    navController.goPage("$ROUTE_2")
                })
                LazyColumn(content = {
                    items(20) {
                        ListItem(headlineText = {
                            Text(text = "$it")
                        })
                    }
                })
            }
        }
        //PAGE 2
        composable(route = ROUTE_2) {
            Box(modifier = Modifier.background(color = Color.Blue).semantics {
                contentDescription = "desc_page_2"
            }) {
                TextButton(onClick = {
                    navController.goPage("$ROUTE_3/$data")
                }) {
                    Text(text = "second", fontSize = 63.sp)
                }
            }
        }
        //PAGE 3
        composable(
            route = "$ROUTE_3/{$param}",//todo 此处param外侧一定要加{}。
            arguments = listOf(
                navArgument(param) {
                    type = NavType.StringType
                }),
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "rally://$ROUTE_3/{$param}"//todo 同样，此处param外侧一定要加{}。
                }
            )
        ) { navBackStackEntry ->
            val nameValue = navBackStackEntry.arguments?.getString(param)
            Box(modifier = Modifier.background(Color.Green)) {
                Text(text = "third_$nameValue", fontSize = 63.sp)
            }
        }
    }

}