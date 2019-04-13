package soup.gdg.navigation.sample.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

/**
 * Activity
 * |- NavHostFragment
 * |  |- Fragment (At this point)
 * |  |  |- NavHostFragment (Find this NavController)
 * |  |  |  |- Fragment
 *
 * @see NavController
 */
fun Fragment.findNestedNavController(): NavController? =
    childFragmentManager.primaryNavigationFragment?.findNavController()

/**
 * Activity
 * |- NavHostFragment (Find this NavController)
 * |  |- Fragment
 * |  |  |- NavHostFragment
 * |  |  |  |- Fragment (At this point)
 *
 * @see NavController
 */
fun Fragment.findParentNavController(): NavController? =
    parentFragment?.parentFragment?.findNavController()
