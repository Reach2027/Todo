/*
 * Copyright 2022 Reach2027
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.reach.uilayer.ui.activity

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIos
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.reach.uilayer.navigation.AppNavGraph
import com.reach.uilayer.navigation.BottomSections
import com.reach.uilayer.navigation.navBack
import com.reach.uilayer.navigation.navToBottomBarRoute
import com.reach.uilayer.theme.TodoTheme
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * 2022/1/31  Reach
 */
@ExperimentalAnimationApi
@Composable
fun ActivityScreen(activityViewModel: ActivityViewModel) {

    val scaffoldState = rememberScaffoldState()
    // Snackbar
    LaunchedEffect(scaffoldState.snackbarHostState) {
        launch {
            activityViewModel.snackbarMessage.collectLatest { message ->
                if (message.isNotEmpty()) {
                    scaffoldState.snackbarHostState.showSnackbar(message)
                    activityViewModel.showMessage("")
                }
            }
        }
    }

    TodoTheme {
        val navController: NavHostController = rememberAnimatedNavController()
        val currentRoute: String? =
            navController.currentBackStackEntryAsState().value?.destination?.route

        activityViewModel.setCurrentRoute(currentRoute)
        val uiState by activityViewModel.uiState.collectAsState()

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
                if (uiState.showBottomBar) {
                    AppBottomBar(
                        uiState.bottomSections,
                        uiState.currentRoute
                    ) { navController.navToBottomBarRoute(it) }
                }
            }
        ) { innerPadding ->
            AppNavGraph(
                activityViewModel = activityViewModel,
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
    sections: List<BottomSections>,
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
