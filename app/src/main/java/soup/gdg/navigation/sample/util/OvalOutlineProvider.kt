package soup.gdg.navigation.sample.util

import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider

var View.clipToOval: Boolean
    get() = clipToOutline && outlineProvider is OvalOutlineProvider
    set(value) {
        clipToOutline = value
        outlineProvider = if (value) OvalOutlineProvider else null
    }

object OvalOutlineProvider : ViewOutlineProvider() {

    override fun getOutline(view: View, outline: Outline) {
        outline.setOval(
            view.paddingLeft,
            view.paddingTop,
            view.width - view.paddingRight,
            view.height - view.paddingBottom
        )
    }
}
