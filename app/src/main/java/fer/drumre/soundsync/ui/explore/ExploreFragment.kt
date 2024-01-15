package fer.drumre.soundsync.ui.explore

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
import fer.drumre.soundsync.databinding.FragmentExploreBinding
import fer.drumre.soundsync.ui.explore.ui.ExploreScreen
import fer.drumre.soundsync.ui.profile.ProfileFragment

@AndroidEntryPoint
class ExploreFragment : BaseFragment() {

    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!

    private val exploreViewModel: ExploreViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.composeView.setContent {
            SoundSyncTheme {
                ExploreScreen(
                    exploreViewModel = exploreViewModel,
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        exploreViewModel.onResume()
    }
}
