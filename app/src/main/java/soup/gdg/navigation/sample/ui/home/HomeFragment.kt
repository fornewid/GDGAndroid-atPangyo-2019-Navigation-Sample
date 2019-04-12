package soup.gdg.navigation.sample.ui.home

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.core.view.postDelayed
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home_content.*
import soup.gdg.navigation.sample.Dependency
import soup.gdg.navigation.sample.R
import soup.gdg.navigation.sample.ui.*
import soup.gdg.navigation.sample.util.lazyUnsafe

class HomeFragment : Fragment(), OnBackPressedListener {

    private val listAdapter = MovieListAdapter { movie ->
        findNavController().navigate(
            HomeFragmentDirections.actionToDetail(movie.id)
        )
    }

    private val listener by lazyUnsafe {
        object : OnNavigationViewClickListener {

            override fun onHeaderClicked(index: Int) {
                drawerLayout.closeDrawer(GravityCompat.START)
                findNavController().navigate(
                    HomeFragmentDirections.actionToProfile()
                )
            }

            override fun onItemClicked(itemId: Int) {
                drawerLayout.closeDrawer(GravityCompat.START)
                when (itemId) {
                    R.id.nav_bookmark -> findNavController().navigate(
                        HomeFragmentDirections.actionToBookmark()
                    )
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        prepareTransitions()
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

        val toggle = ActionBarDrawerToggle(
            activity, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setCheckedItem(R.id.nav_home)
        navigationView.setOnNavigationViewClickListener(listener)

        listView.adapter = listAdapter
        showLoading()
        view.postDelayed(500) {
            Dependency.repository.getMovieList().let {
                listAdapter.submitList(it)
                emptyLabel.isVisible = it.isEmpty()
            }
            hideLoading()
        }
    }

    override fun onBackPressed(): Boolean {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
            return true
        }
        return false
    }
}
