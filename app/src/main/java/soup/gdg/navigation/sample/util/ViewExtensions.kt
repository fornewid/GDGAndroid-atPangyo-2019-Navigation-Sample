package soup.gdg.navigation.sample.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import com.bumptech.glide.Priority
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import soup.gdg.navigation.sample.data.model.Url

fun ViewGroup.inflate(@LayoutRes resId: Int): View {
    return LayoutInflater.from(context).inflate(resId, this, false)
}

fun ImageView.loadImageAsync(url: Url?) {
    GlideApp.with(context)
        .load(url)
        .priority(Priority.IMMEDIATE)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}
