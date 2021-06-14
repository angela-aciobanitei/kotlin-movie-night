package com.ang.acb.movienight.ui.main

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun MainBottomNavigation(
    navController: NavHostController,
    items: List<MainBottomNavItem>
) {
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route ?: MainDestinations.DISCOVER

        items.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(painter = painterResource(screen.iconResId), contentDescription = null)
                },
                label = {
                    Text(stringResource(id = screen.labelResId))
                },
                selected = currentRoute == screen.route,
                onClick = {
                    // Use the latest navigate() method that takes a navOptions DSL Builder
                    // See: https://developer.android.com/jetpack/androidx/releases/navigation#2.4.0-alpha01
                    navController.navigate(screen.route) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                    }
                }
            )
        }
    }
}