package soup.gdg.navigation.sample.ui.home

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home_content.*
import soup.gdg.navigation.sample.Dependency
import soup.gdg.navigation.sample.NavigationDirections
import soup.gdg.navigation.sample.R
import soup.gdg.navigation.sample.ui.OnBackPressedListener
import soup.gdg.navigation.sample.ui.OnNavigationViewClickListener
import soup.gdg.navigation.sample.ui.login.LoginConfirmDialogFragment
import soup.gdg.navigation.sample.ui.setOnNavigationViewClickListener
import soup.gdg.navigation.sample.util.lazyUnsafe

class BookmarkFragment : Fragment(), OnBackPressedListener {

    private val listAdapter = MovieListAdapter { movie ->
        findNavController().navigate(
            BookmarkFragmentDirections.actionToDetail(movie.id)
        )
    }

    private val listener by lazyUnsafe {
        object : OnNavigationViewClickListener {

            override fun onHeaderClicked(index: Int) {
                drawerLayout.closeDrawer(GravityCompat.START)
                if (Dependency.repository.isSignedIn()) {
                    findNavController().navigate(
                        HomeFragmentDirections.actionToProfile()
                    )
                } else {
                    LoginConfirmDialogFragment.show(this@BookmarkFragment, REQUEST_LOGIN_CONFIRM)
                }
            }

            override fun onItemClicked(itemId: Int) {
                drawerLayout.closeDrawer(GravityCompat.START)
                when (itemId) {
                    R.id.nav_home -> findNavController().popBackStack(R.id.home, false)
                    R.id.nav_settings -> findNavController().navigate(
                        BookmarkFragmentDirections.actionToSettings()
                    )
                    R.id.nav_github -> findNavController().navigate(
                        BookmarkFragmentDirections.actionToWeb(
                            title = "Github",
                            url = "https://github.com/fornewid/GDGAndroid-atPangyo-2019-Navigation-Sample"
                        )
                    )
                }
            }
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
        toolbar.setTitle(R.string.title_bookmark)

        val activity = requireActivity()
        val toggle = ActionBarDrawerToggle(
            activity, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setCheckedItem(R.id.nav_bookmark)
        navigationView.setOnNavigationViewClickListener(listener)

        listView.adapter = listAdapter
        Dependency.repository.getBookmarkMovieList().let {
            listAdapter.submitList(it)
            emptyLabel.isVisible = it.isEmpty()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_LOGIN_CONFIRM && resultCode == RESULT_OK) {
            findNavController().navigate(
                NavigationDirections.actionToLogin(nextDestinationIsUp = true)
            )
        }
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
