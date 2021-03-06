package soup.gdg.navigation.sample.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_bookmark.*
import soup.gdg.navigation.sample.Dependency
import soup.gdg.navigation.sample.R
import soup.gdg.navigation.sample.navigation.findParentNavController

class BookmarkFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_bookmark, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listView.adapter = listAdapter
        Dependency.repository.getBookmarkMovieList().let {
            listAdapter.submitList(it)
            emptyLabel.isVisible = it.isEmpty()
        }
    }
}
