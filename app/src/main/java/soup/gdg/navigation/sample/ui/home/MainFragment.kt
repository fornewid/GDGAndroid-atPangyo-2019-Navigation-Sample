package soup.gdg.navigation.sample.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.view.postDelayed
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_main.*
import soup.gdg.navigation.sample.Dependency
import soup.gdg.navigation.sample.R
import soup.gdg.navigation.sample.navigation.findParentNavController
import soup.gdg.navigation.sample.ui.hideLoading
import soup.gdg.navigation.sample.ui.showLoading

class MainFragment : Fragment() {

    private val listAdapter = MovieListAdapter { movie ->
        findParentNavController()?.navigate(
            HomeFragmentDirections.actionToDetail(movie.id)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
}
