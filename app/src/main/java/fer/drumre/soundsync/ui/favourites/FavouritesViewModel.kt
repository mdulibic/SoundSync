package fer.drumre.soundsync.ui.favourites

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fer.drumre.soundsync.domain.SessionManager
import fer.drumre.soundsync.ui.favourites.model.FavouritesUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val sessionManager: SessionManager,
) : ViewModel() {

    private val _uiState = MutableStateFlow<FavouritesUiState?>(null)
    val uiState: StateFlow<FavouritesUiState?> = _uiState

    val initial: String? = sessionManager.userName?.get(0).toString()
}
