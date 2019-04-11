package soup.gdg.navigation.sample.ui

import androidx.fragment.app.Fragment

interface LoadingViewOwner {

    fun showLoadingView()
    fun hideLoadingView()
}

fun Fragment.showLoading() {
    val root = activity
    if (root is LoadingViewOwner) {
        root.showLoadingView()
    }
}

fun Fragment.hideLoading() {
    val root = activity
    if (root is LoadingViewOwner) {
        root.hideLoadingView()
    }
}
