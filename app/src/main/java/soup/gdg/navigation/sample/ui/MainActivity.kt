package soup.gdg.navigation.sample.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.activity_main.*
import soup.gdg.navigation.sample.NavigationDirections
import soup.gdg.navigation.sample.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        intent?.handleDeepLink()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.handleDeepLink()
    }

    private fun Intent.handleDeepLink() {
        if (scheme != "custom" || data?.host != "detail") return
        val id = data?.getQueryParameter("id") ?: return
        navHostFragment.findNavController().apply {
            if (currentDestination == null) {
                navigate(NavigationDirections.actionToDetail(id))
            } else {
                popBackStack(R.id.home, false)
                navigate(NavigationDirections.actionToDetail(id))
            }
        }
    }

    override fun onBackPressed() {
        if (handleBackEventInChildFragment()) return
        super.onBackPressed()
    }

    private fun handleBackEventInChildFragment(): Boolean {
        val current = navHostFragment.childFragmentManager.fragments.elementAtOrNull(0)
        if (current is OnBackPressedListener) {
            return current.onBackPressed()
        }
        return false
    }
}
