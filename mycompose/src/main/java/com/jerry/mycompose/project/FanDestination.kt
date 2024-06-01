package com.jerry.mycompose.project

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed interface FanDestination {
    val icon: ImageVector
    val route: String
}

object Main : FanDestination {
    override val icon: ImageVector
        get() = Icons.Default.Home
    override val route: String
        get() = "Home"

}

object Study : FanDestination {
    override val icon: ImageVector
        get() = Icons.Default.ShoppingCart
    override val route: String
        get() = "Study"

}

object Mine : FanDestination {
    override val icon: ImageVector
        get() = Icons.Default.Person
    override val route: String
        get() = "Mine"
}

val fanTabScreens = listOf<FanDestination>(Main, Study, Mine)