package soup.gdg.navigation.sample.ui.home

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import soup.gdg.navigation.sample.R
import soup.gdg.navigation.sample.data.model.Movie
import soup.gdg.navigation.sample.util.IdBasedDiffCallback
import soup.gdg.navigation.sample.util.inflate
import soup.gdg.navigation.sample.util.loadImageAsync

class MovieListAdapter(
    private val listener: (Movie) -> Unit
) : ListAdapter<Movie, MovieViewHolder>(IdBasedDiffCallback { id }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(parent.inflate(R.layout.item_movie)).apply {
            itemView.setOnClickListener {
                getItem(adapterPosition)?.run(listener)
            }
        }
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.bind(item)
    }
}

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val posterView: ImageView = view.findViewById(R.id.posterView)
    private val favoriteIcon: ImageView = view.findViewById(R.id.favoriteIcon)

    fun bind(movie: Movie) {
        posterView.loadImageAsync(movie.posterUrl)
        favoriteIcon.setImageResource(
            if (movie.favorite) {
                R.drawable.ic_favorite_on
            } else {
                R.drawable.ic_favorite_off
            }
        )
    }
}
