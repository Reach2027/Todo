package com.reach.todo.ui.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

/**
 * 2022/2/4  Reach
 */

fun NavHostController.navToBottomBarRoute(route: String) {
    navigate(route) {
        launchSingleTop = true
        restoreState = true
        popUpTo(this@navToBottomBarRoute.graph.findStartDestination().id) {
            saveState = true
        }
    }
}

fun NavHostController.navBack(): () -> Unit {
    return { navigateUp() }
}
