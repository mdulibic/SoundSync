package fer.drumre.soundsync.ui.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import fer.digobr.kidslingo.theme.SoundSyncTheme
import fer.drumre.soundsync.core.BaseFragment
import fer.drumre.soundsync.databinding.FragmentFavouritesBinding
import fer.drumre.soundsync.ui.favourites.ui.FavouritesScreen

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
                        favouritesViewModel.signOut()
                    },
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
