package io.spherelabs.cosmokmp.android.ui.discover

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavComponent(
    modifier: Modifier = Modifier,
    navController: NavController,
    backgroundColor: Color,
    contentColor: Color
) {
    BottomNavigation(
        modifier = modifier.fillMaxWidth(),
        backgroundColor = backgroundColor,
        contentColor = contentColor
    ) {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry.value?.destination?.route

        bottomNavItems.forEach { newNavItem ->
            BottomNavigationItem(
                icon = {
                    Image(
                        modifier = modifier.size(26.dp),
                        painter = painterResource(id = newNavItem.icon),
                        contentDescription = null
                    )
                },
                selected = currentRoute == newNavItem.route,
                onClick = {
                    navController.navigate(newNavItem.route) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
