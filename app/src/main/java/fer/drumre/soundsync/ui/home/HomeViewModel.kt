package fer.drumre.soundsync.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fer.drumre.soundsync.domain.SessionManager
import fer.drumre.soundsync.domain.usecase.GetArtistsUseCase
import fer.drumre.soundsync.domain.usecase.GetGenresUseCase
import fer.drumre.soundsync.ui.home.mapper.HomeMapper
import fer.drumre.soundsync.ui.home.model.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val getArtistsUseCase: GetArtistsUseCase,
    private val getGenresUseCase: GetGenresUseCase,
    private val homeMapper: HomeMapper,
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState?>(null)
    val uiState: StateFlow<HomeUiState?> = _uiState

    val initial: String? = sessionManager.userName?.get(0).toString()

    init {
        getHomeData()
    }

    fun signOut() {
        sessionManager.isLoggedIn = false
        sessionManager.userName = null
    }

    private fun getHomeData() {
        viewModelScope.launch {
            combine(
                getArtistsUseCase(),
                getGenresUseCase(),
                ::Pair,
            ).map { (artists, genres) ->
                homeMapper.mapToUiState(
                    artists = artists,
                    genres = genres,
                )
            }.collectLatest {
                _uiState.value = it
            }
        }
    }
}
