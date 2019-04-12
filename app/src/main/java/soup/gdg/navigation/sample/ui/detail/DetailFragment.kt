package soup.gdg.navigation.sample.ui.detail

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_detail.*
import soup.gdg.navigation.sample.Dependency
import soup.gdg.navigation.sample.NotificationChannels
import soup.gdg.navigation.sample.R
import soup.gdg.navigation.sample.data.model.Movie
import soup.gdg.navigation.sample.util.loadImageAsync

class DetailFragment : Fragment() {

    private val args: DetailFragmentArgs by navArgs()
    private var movie: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.transition_detail_enter)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setTitle(R.string.title_detail)
        originFab.setOnClickListener {
            notifyDeepLinkNotification(view.context, "origin://detail/${args.movieId}")
        }
        customFab.setOnClickListener {
            notifyDeepLinkNotification(view.context, "custom://detail?id=${args.movieId}")
        }
        favoriteFab.setOnClickListener {
            toggleFavoriteState()
        }
        updateContent()
    }

    private fun toggleFavoriteState() {
        val movie = movie ?: return
        if (movie.favorite) {
            Dependency.repository.removeBookmark(movie)
        } else {
            Dependency.repository.addBookmark(movie)
        }
        updateContent()
    }

    private fun updateContent() {
        Dependency.repository.getMovieDetail(args.movieId).let {
            movie = it
            renderContents(it)
        }
    }

    private fun renderContents(movie: Movie) {
        posterView.loadImageAsync(movie.posterUrl)
        if (movie.favorite) {
            favoriteFab.setImageResource(R.drawable.ic_favorite_on)
        } else {
            favoriteFab.setImageResource(R.drawable.ic_favorite_off)
        }
    }

    private fun notifyDeepLinkNotification(context: Context, deepLink: String) {
        NotificationChannels.notify(context) {
            setSmallIcon(R.drawable.ic_notification)
            setContentTitle("DeepLink Test")
            setContentText("Please click to execute deepLink.")
            setAutoCancel(true)
            setContentIntent(context.createDeepLinkIntent(deepLink))
        }
    }

    private fun Context.createDeepLinkIntent(deepLink: String): PendingIntent {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(deepLink))
        return PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
    }
}
