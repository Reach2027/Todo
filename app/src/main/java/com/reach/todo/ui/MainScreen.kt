package com.reach.todo.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.reach.todo.BottomSections
import com.reach.todo.ui.components.AppAnimatedVisibility
import com.reach.todo.ui.navigation.AppNavGraph
import com.reach.todo.ui.navigation.navBack
import com.reach.todo.ui.navigation.navToBottomBarRoute
import com.reach.todo.ui.theme.TodoTheme

/**
 * 2022/1/31  Reach
 */
@Suppress("EXPERIMENTAL_IS_NOT_ENABLED")
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TaskApp(mainViewModel: MainViewModel) {
    TodoTheme {
        val navController: NavHostController = rememberNavController()
        val currentRoute: String? =
            navController.currentBackStackEntryAsState().value?.destination?.route

        mainViewModel.setCurrentRoute(currentRoute)
        val uiState by mainViewModel.uiState.collectAsState()

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                AppTopBar(
                    showBackIcon = uiState.showBackIcon,
                    title = uiState.title,
                    onBack = navController.navBack()
                )
            },
            bottomBar = {
                AppAnimatedVisibility(uiState.showBottomBar) {
                    AppBottomBar(
                        mainViewModel.bottomSections,
                        uiState.currentRoute
                    ) { navController.navToBottomBarRoute(it) }
                }
            }) { innerPadding ->
            AppNavGraph(
                navController,
                modifier = Modifier
                    .padding(innerPadding)
                    .animateContentSize()
            )
        }
    }
}

@Composable
private fun AppTopBar(
    showBackIcon: Boolean,
    title: String,
    onBack: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = if (showBackIcon) {
            { AppBackIcon(onBack = onBack) }
        } else {
            null
        },
        backgroundColor = MaterialTheme.colors.primary
    )
}

@Composable
private fun AppBackIcon(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onBack,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Outlined.ArrowBackIos,
            contentDescription = "Back"
        )
    }
}

@Composable
private fun AppBottomBar(
    sections: Array<BottomSections>,
    currentRoute: String,
    navigateToRoute: (String) -> Unit
) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primary
    ) {
        for (section in sections) {
            val selected = section.route == currentRoute
            BottomNavigationItem(
                selected = selected,
                onClick = { navigateToRoute(section.route) },
                icon = {
                    Icon(
                        imageVector =
                        if (selected) section.selectedIcon else section.unselectedIcon,
                        contentDescription = section.route,
                    )
                },
                enabled = !selected
            )
        }
    }
}

fun navToBottomBarRoute(
    navController: NavHostController,
    route: String
) {
    navController.navigate(route) {
        launchSingleTop = true
        restoreState = true
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
    }
}