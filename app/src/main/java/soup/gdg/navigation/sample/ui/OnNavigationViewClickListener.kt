package soup.gdg.navigation.sample.ui

import androidx.annotation.IdRes
import com.google.android.material.navigation.NavigationView

interface OnNavigationViewClickListener {

    fun onHeaderClicked(index: Int)

    fun onItemClicked(@IdRes itemId: Int)
}

fun NavigationView.setOnNavigationViewClickListener(
    listener: OnNavigationViewClickListener
) {
    (0 until headerCount).forEach { index ->
        getHeaderView(index).setOnClickListener {
            listener.onHeaderClicked(index)
        }
    }
    setNavigationItemSelectedListener {
        if (!it.isChecked) {
            listener.onItemClicked(it.itemId)
        }
        true
    }
}
