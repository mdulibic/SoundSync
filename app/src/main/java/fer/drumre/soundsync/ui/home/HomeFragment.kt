package fer.drumre.soundsync.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import fer.digobr.kidslingo.theme.SoundSyncTheme
import fer.drumre.soundsync.R
import fer.drumre.soundsync.core.BaseFragment
import fer.drumre.soundsync.databinding.FragmentHomeBinding
import fer.drumre.soundsync.ui.profile.ProfileFragment

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

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
                        showProfile()
                    },
                )
            }
        }
    }

    private fun showProfile() {
        val profileFragment = ProfileFragment()
        profileFragment.setStyle(
            DialogFragment.STYLE_NORMAL,
            R.style.FullScreenDialogTheme,
        )
        profileFragment.show(
            parentFragmentManager,
            ProfileFragment::class.java.simpleName,
        )
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
