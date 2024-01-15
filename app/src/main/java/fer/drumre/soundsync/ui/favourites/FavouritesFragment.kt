package fer.drumre.soundsync.ui.favourites

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
import fer.drumre.soundsync.databinding.FragmentFavouritesBinding
import fer.drumre.soundsync.ui.favourites.ui.FavouritesScreen
import fer.drumre.soundsync.ui.profile.ProfileFragment

@AndroidEntryPoint
class FavouritesFragment : BaseFragment() {

    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!

    private val favouritesViewModel: FavouritesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.composeView.setContent {
            SoundSyncTheme {
                FavouritesScreen(
                    favouritesViewModel = favouritesViewModel,
                    onInitialClick = {
                        showProfile()
                    },
                    onFavouriteClick = {
                        favouritesViewModel.onFavouriteClick(it)
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
        favouritesViewModel.getFavourites()
    }
}
