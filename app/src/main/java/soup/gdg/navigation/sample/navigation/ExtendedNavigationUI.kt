package soup.gdg.navigation.sample.navigation

import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI

object ExtendedNavigationUI {

    fun setupWithNavController(
        toolbar: Toolbar,
        navController: NavController,
        drawerLayout: DrawerLayout?,
        vararg topLevelDestinationIds: Int
    ) {
        NavigationUI.setupWithNavController(
            toolbar, navController,
            AppBarConfiguration.Builder(topLevelDestinationIds.toSet())
                .setDrawerLayout(drawerLayout)
                .build()
        )
    }
}
