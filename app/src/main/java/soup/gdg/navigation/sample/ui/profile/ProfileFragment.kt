package soup.gdg.navigation.sample.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import kotlinx.android.synthetic.main.fragment_profile.*
import soup.gdg.navigation.sample.Dependency
import soup.gdg.navigation.sample.NavigationDirections
import soup.gdg.navigation.sample.R
import soup.gdg.navigation.sample.util.clipToOval

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.title = "SOUP"
        profileImage.clipToOval = true
        logoutButton.setOnClickListener {
            Dependency.repository.signOut()
            findNavController().navigate(
                NavigationDirections.actionToLogin(),
                navOptions {
                    popUpTo(R.id.home) {
                        inclusive = true
                    }
                }
            )
        }
    }
}
