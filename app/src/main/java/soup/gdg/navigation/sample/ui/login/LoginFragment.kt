package soup.gdg.navigation.sample.ui.login

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_login.*
import soup.gdg.navigation.sample.Dependency
import soup.gdg.navigation.sample.R
import soup.gdg.navigation.sample.ui.ExternalLoginActivity
import soup.gdg.navigation.sample.ui.login.LoginFragmentArgs

class LoginFragment : Fragment() {

    private val args: LoginFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setTitle(R.string.title_login)
        skipButton.setOnClickListener {
            navigateToNextDestination()
        }
        loginButton.setOnClickListener {
            Dependency.repository.signIn()
            startActivityForResult(
                Intent(view.context, ExternalLoginActivity::class.java),
                REQUEST_LOGIN
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_LOGIN && resultCode == RESULT_OK) {
            navigateToNextDestination()
        }
    }

    private fun navigateToNextDestination() {
        if (args.nextDestinationIsUp) {
            findNavController().navigateUp()
        } else {
            findNavController().navigate(
                LoginFragmentDirections.actionToHome()
            )
        }
    }

    companion object {
        private const val REQUEST_LOGIN = 1
    }
}
