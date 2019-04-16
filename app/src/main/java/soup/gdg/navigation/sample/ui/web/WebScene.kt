package soup.gdg.navigation.sample.ui.web

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import soup.gdg.navigation.sample.data.model.Url

@Parcelize
class WebScene(
    val title: String? = null,
    val url: Url
) : Parcelable
