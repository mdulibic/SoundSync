package fer.drumre.soundsync.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.facebook.CallbackManager
import com.facebook.login.LoginManager
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import fer.digobr.kidslingo.theme.SoundSyncTheme
import fer.drumre.soundsync.MainActivity
import fer.drumre.soundsync.core.BaseFragment
import fer.drumre.soundsync.data.model.SaveUserRequest
import fer.drumre.soundsync.databinding.FragmentLoginBinding
import fer.drumre.soundsync.ui.login.model.LoginSourceType
import fer.drumre.soundsync.ui.login.ui.LoginScreen
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class LoginFragment : BaseFragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel: LoginViewModel by viewModels()

    private lateinit var googleSignInClient: GoogleSignInClient

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            Timber.d("ActivityResult: resultCode=${result.resultCode}")
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.let {
                    Timber.d("Handling sign-in result")
                    val googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(it)
                    handleSignInResult(googleSignInResult!!)
                }
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

        binding.composeView.setContent {
            SoundSyncTheme {
                LoginScreen(
                    loginViewModel = loginViewModel,
                    onCtaClick = { onCtaClicked(it) },
                )
            }
        }
    }

    private fun onCtaClicked(loginType: LoginSourceType) {
        when (loginType) {
            LoginSourceType.FB -> signInWithFb()
            LoginSourceType.GOOGLE -> signInWithGoogle()
        }
    }

    private fun signInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        startForResult.launch(signInIntent)
    }

    private val signInWithFb = {
        if (loginViewModel.checkFbProfile()) {
            navigateToHome()
        } else {
            LoginManager.getInstance()
                .logIn(requireActivity(), CallbackManager.Factory.create(), listOf())
        }
    }

    private fun handleSignInResult(result: GoogleSignInResult) {
        if (result.isSuccess) {
            val account = result.signInAccount
            val snackbar = Snackbar.make(
                requireView(),
                "Hello, ${account?.displayName}",
                Snackbar.LENGTH_SHORT,
            )
            snackbar.show()
            snackbar.addCallback(object : BaseTransientBottomBar.BaseCallback<Snackbar>() {
                override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                    loginViewModel.saveUser(
                        userInfo = SaveUserRequest(
                            name = account?.givenName ?: "",
                            surname = account?.familyName ?: "",
                            email = account?.email ?: "",
                        ),
                    )
                    navigateToHome()
                }
            })
        } else {
            Snackbar.make(requireView(), "Sign-in failed, try again!", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun navigateToHome() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
