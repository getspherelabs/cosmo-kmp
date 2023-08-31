package io.spherelabs.cosmokmp.android.ui.discover

import io.spherelabs.cosmokmp.android.R

val bottomNavItems = listOf(
    BottomNavItem.Search,
    BottomNavItem.Discover,
    BottomNavItem.Favourite,
    BottomNavItem.Setting
)

sealed class BottomNavItem(
    val title: Int,
    val icon: Int,
    val route: String
) {
    object Search : BottomNavItem(title = R.string.search, icon = R.drawable.ic_search, "search")
    object Discover : BottomNavItem(title = R.string.discover, icon = R.drawable.ic_discover, "discover")
    object Favourite : BottomNavItem(title = R.string.favourite, icon = R.drawable.ic_favourite, "favourite")
    object Setting : BottomNavItem(title = R.string.about, icon = R.drawable.ic_profile, "about")
}
