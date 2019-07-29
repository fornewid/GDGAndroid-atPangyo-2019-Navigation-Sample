package soup.gdg.navigation.sample.navigation

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

class NavHostFragmentEx : NavHostFragment() {

    override fun onCreateNavController(navController: NavController) {
        super.onCreateNavController(navController)
        navController.navigatorProvider.addNavigator(
            DialogFragmentNavigator(requireContext(), childFragmentManager)
        )
    }
}
