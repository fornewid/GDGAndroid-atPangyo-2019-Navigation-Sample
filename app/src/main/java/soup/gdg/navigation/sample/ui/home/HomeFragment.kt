package soup.gdg.navigation.sample.ui.home

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.core.view.postOnAnimationDelayed
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.fragment_home.*
import soup.gdg.navigation.sample.R
import soup.gdg.navigation.sample.ui.OnBackPressedListener

class HomeFragment : Fragment(), OnBackPressedListener, NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        prepareTransitions()
        postponeEnterTransition()
    }

    private fun prepareTransitions() {
        exitTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.transition_grid_exit)
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
        toolbar.setTitle(R.string.title_home)

        val activity = requireActivity()

        val toggle = ActionBarDrawerToggle(
            activity, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigationView.setNavigationItemSelectedListener(this)
        navigationView.getHeaderView(0).setOnClickListener {
            drawerLayout.closeDrawer(GravityCompat.START)
            findNavController().navigate(
                HomeFragmentDirections.actionToProfile()
            )
        }

        view.postOnAnimationDelayed(100) {
            startPostponedEnterTransition()
        }
    }

    override fun onBackPressed(): Boolean {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
            return true
        }
        return false
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawerLayout.closeDrawer(GravityCompat.START)
        if (item.isChecked) {
            return true
        }
        when (item.itemId) {
            R.id.nav_home -> {
                findNavController().navigate(
                    HomeFragmentDirections.actionToHome()
                )
            }
            R.id.nav_bookmark -> {
                findNavController().navigate(
                    HomeFragmentDirections.actionToBookmark()
                )
            }
            R.id.nav_settings -> {
                findNavController().navigate(
                    HomeFragmentDirections.actionToSettings()
                )
            }
            R.id.nav_send -> {
                findNavController().navigate(
                    HomeFragmentDirections.actionToWeb(
                        title = "Source Code",
                        url = "https://github.com/fornewid/GDGAndroid-atPangyo-2019-Navigation-Sample"
                    )
                )
            }
        }
        return true
    }
}
