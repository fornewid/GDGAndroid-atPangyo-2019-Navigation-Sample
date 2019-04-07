package soup.gdg.navigation.sample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_external_login.*
import soup.gdg.navigation.sample.R

class ExternalLoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_external_login)
        confirmButton.setOnClickListener {
            setResult(RESULT_OK)
            finish()
        }
        cancelButton.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }
}
