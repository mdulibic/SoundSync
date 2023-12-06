package fer.drumre.soundsync.ui.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fer.drumre.soundsync.domain.SessionManager
import fer.drumre.soundsync.ui.home.model.ProfileUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val sessionManager: SessionManager,
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProfileUiState?>(null)
    val uiState: StateFlow<ProfileUiState?> = _uiState

    private val _navigateToHome = MutableSharedFlow<Unit>()
    val navigateToHome: SharedFlow<Unit> = _navigateToHome

    val initial: String? = sessionManager.userName?.get(0).toString()

    init {
        getHomeData()
    }

    private fun getHomeData() {
    }
}
