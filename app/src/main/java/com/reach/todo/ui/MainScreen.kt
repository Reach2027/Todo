package com.reach.todo.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.reach.todo.BottomSections
import com.reach.todo.ui.components.AppAnimatedVisibility
import com.reach.todo.ui.navigation.AppNavGraph
import com.reach.todo.ui.navigation.navBack
import com.reach.todo.ui.navigation.navToBottomBarRoute
import com.reach.todo.ui.theme.TodoTheme
import kotlinx.coroutines.flow.collectLatest

/**
 * 2022/1/31  Reach
 */
@Composable
fun TaskApp(mainViewModel: MainViewModel) {

    val scaffoldState = rememberScaffoldState()
    // Snackbar
    LaunchedEffect(scaffoldState.snackbarHostState) {
        mainViewModel.snackbarMessage.collectLatest { message ->
            if (message.isNotEmpty()) {
                scaffoldState.snackbarHostState.showSnackbar(message)
                mainViewModel.showMessage("")
            }
        }
    }

    TodoTheme {
        val navController: NavHostController = rememberNavController()
        val currentRoute: String? =
            navController.currentBackStackEntryAsState().value?.destination?.route

        mainViewModel.setCurrentRoute(currentRoute)
        val uiState by mainViewModel.uiState.collectAsState()

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            scaffoldState = scaffoldState,
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
                mainViewModel = mainViewModel,
                navController = navController,
                modifier = Modifier
                    .padding(innerPadding)

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
