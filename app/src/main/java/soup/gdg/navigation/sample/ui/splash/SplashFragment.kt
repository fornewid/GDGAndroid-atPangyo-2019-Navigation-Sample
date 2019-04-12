package soup.gdg.navigation.sample.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.postOnAnimationDelayed
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import soup.gdg.navigation.sample.Dependency
import soup.gdg.navigation.sample.R
import soup.gdg.navigation.sample.ui.splash.SplashFragmentDirections.Companion.actionToHome
import soup.gdg.navigation.sample.ui.splash.SplashFragmentDirections.Companion.actionToLogin

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onResume() {
        super.onResume()
        view?.postOnAnimationDelayed(500) {
            val isSignedIn = Dependency.repository.isSignedIn()
            if (isSignedIn) {
                findNavController().navigate(actionToHome())
            } else {
                findNavController().navigate(actionToLogin())
            }
        }
    }
}
