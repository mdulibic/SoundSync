package fer.drumre.soundsync.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.hilt.android.AndroidEntryPoint
import fer.digobr.kidslingo.theme.SoundSyncTheme
import fer.drumre.soundsync.core.BaseFragment
import fer.drumre.soundsync.databinding.FragmentHomeBinding
import fer.drumre.soundsync.ui.home.ui.HomeScreen
import fer.drumre.soundsync.ui.login.LoginActivity

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

    private val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .build()

    // Create the GoogleSignInClient
    private val googleSignInClient: GoogleSignInClient by lazy {
        GoogleSignIn.getClient(requireActivity(), gso)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.composeView.setContent {
            SoundSyncTheme {
                HomeScreen(
                    homeViewModel = homeViewModel,
                    onInitialClick = {
                        homeViewModel.signOut()
                        signOut()
                    },
                )
            }
        }
    }

    private fun signOut() {
        googleSignInClient.signOut().addOnCompleteListener { status ->
            if (status.isSuccessful) {
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            } else {
                // Handle sign-out failure
                // You might want to notify the user or log the error
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
