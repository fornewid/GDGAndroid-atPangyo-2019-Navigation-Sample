package soup.gdg.navigation.sample.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.activity_main.*
import soup.gdg.navigation.sample.NavigationDirections
import soup.gdg.navigation.sample.R

class MainActivity : AppCompatActivity(), LoadingViewOwner {

    override fun showLoadingView() {
        loadingView?.isVisible = true
    }

    override fun hideLoadingView() {
        loadingView?.isVisible = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        intent?.handleDeepLinkInCustom()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.handleDeepLinkInCustom()
    }

    private fun Intent.handleDeepLinkInCustom() {
        if (scheme != "custom" || data?.host != "detail") return
        val id = data?.getQueryParameter("id") ?: return
        navHostFragment.findNavController().apply {
            navigate(NavigationDirections.actionToDetail(id))
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
