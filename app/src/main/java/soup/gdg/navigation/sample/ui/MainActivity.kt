package soup.gdg.navigation.sample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import soup.gdg.navigation.sample.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
