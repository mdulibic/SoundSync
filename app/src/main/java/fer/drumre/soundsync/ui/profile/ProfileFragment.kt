package fer.drumre.soundsync.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import fer.digobr.kidslingo.theme.SoundSyncTheme
import fer.drumre.soundsync.R
import fer.drumre.soundsync.databinding.FragmentProfileBinding
import fer.drumre.soundsync.ui.login.LoginActivity
import fer.drumre.soundsync.ui.profile.ui.ProfileScreen
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : DialogFragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogTheme)
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT,
        )

        binding.composeView.setContent {
            SoundSyncTheme {
                ProfileScreen(
                    profileViewModel = profileViewModel,
                    onCloseClick = {
                        dismiss()
                    },
                )
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            profileViewModel.navigateToLogin.collect {
                navigateToLogin()
            }
        }
    }

    private fun navigateToLogin() {
        val intent = Intent(requireContext(), LoginActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
