package soup.gdg.navigation.sample.ui.home

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home_profile.view.*
import soup.gdg.navigation.sample.Dependency
import soup.gdg.navigation.sample.HomeNavGraphDirections
import soup.gdg.navigation.sample.NavigationDirections
import soup.gdg.navigation.sample.R
import soup.gdg.navigation.sample.navigation.ExtendedNavigationUI
import soup.gdg.navigation.sample.navigation.findNestedNavController
import soup.gdg.navigation.sample.ui.OnBackPressedListener
import soup.gdg.navigation.sample.ui.OnNavigationViewClickListener
import soup.gdg.navigation.sample.ui.login.LoginConfirmDialogFragment
import soup.gdg.navigation.sample.ui.setOnNavigationViewClickListener
import soup.gdg.navigation.sample.util.clipToOval
import soup.gdg.navigation.sample.util.lazyUnsafe

class HomeFragment : Fragment(), OnBackPressedListener {

    private val listener by lazyUnsafe {
        object : OnNavigationViewClickListener {

            override fun onHeaderClicked(index: Int) {
                drawerLayout.closeDrawer(GravityCompat.START)
                doAfterSignIn {
                    findNavController().navigate(
                        HomeFragmentDirections.actionToProfile()
                    )
                }
            }

            override fun onItemClicked(itemId: Int) {
                drawerLayout.closeDrawer(GravityCompat.START)
                when (itemId) {
                    R.id.nav_home -> findNestedNavController().popBackStack(R.id.main, false)
                    R.id.nav_bookmark -> doAfterSignIn {
                        findNestedNavController().navigate(
                            HomeNavGraphDirections.actionToBookmark()
                        )
                    }
                    R.id.nav_settings -> findNavController().navigate(
                        HomeFragmentDirections.actionToSettings()
                    )
                    R.id.nav_github -> findNavController().navigate(
                        HomeFragmentDirections.actionToWeb(
                            title = "Github",
                            url = "https://github.com/fornewid/GDGAndroid-atPangyo-2019-Navigation-Sample"
                        )
                    )
                }
            }
        }
    }

    private fun doAfterSignIn(action: () -> Unit) {
        if (Dependency.repository.isSignedIn()) {
            action()
        } else {
            LoginConfirmDialogFragment.show(this@HomeFragment, REQUEST_LOGIN_CONFIRM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ExtendedNavigationUI.setupWithNavController(
            toolbar, findNestedNavController(), drawerLayout,
            R.id.main, R.id.bookmark
        )
        navigationView.setOnNavigationViewClickListener(listener)
    }

    override fun onResume() {
        super.onResume()
        refreshProfile()
    }

    private fun refreshProfile() {
        navigationView.getHeaderView(0)?.run {
            profileImage.clipToOval = true
            if (Dependency.repository.isSignedIn()) {
                profileImage.setImageResource(R.drawable.profile_user)
                nicknameText.text = "SOUP"
            } else {
                profileImage.setImageResource(R.drawable.profile_guest)
                nicknameText.text = "로그인이 필요합니다"
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_LOGIN_CONFIRM && resultCode == RESULT_OK) {
            findNavController().navigate(
                NavigationDirections.actionToLogin(nextDestinationIsUp = true)
            )
        }
        //TODO: Where is the login result?
    }

    override fun onBackPressed(): Boolean {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
            return true
        }
        return false
    }

    companion object {
        private const val REQUEST_LOGIN_CONFIRM = 1
    }
}
