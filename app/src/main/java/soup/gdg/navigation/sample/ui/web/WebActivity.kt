package soup.gdg.navigation.sample.ui.web

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import kotlinx.android.synthetic.main.activity_web.*
import soup.gdg.navigation.sample.R

class WebActivity : AppCompatActivity() {

    private val args: WebActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        toolbar.title = args.data.title ?: "No Title"
        webView.loadUrl(args.data.url)
    }
}
