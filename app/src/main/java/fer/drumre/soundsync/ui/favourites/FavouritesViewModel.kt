package fer.drumre.soundsync.ui.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fer.drumre.soundsync.domain.SessionManager
import fer.drumre.soundsync.domain.usecase.GetFavouritesUseCase
import fer.drumre.soundsync.domain.usecase.ManageFavouritesUseCase
import fer.drumre.soundsync.ui.favourites.model.FavouritesUiState
import fer.drumre.soundsync.ui.home.model.Favourite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val manageFavouritesUseCase: ManageFavouritesUseCase,
    private val getFavouritesUseCase: GetFavouritesUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<FavouritesUiState?>(null)
    val uiState: StateFlow<FavouritesUiState?> = _uiState

    val initial: String? = sessionManager.userName?.get(0).toString()

    fun signOut() {
        sessionManager.isLoggedIn = false
        sessionManager.userName = null
    }

    init {
        getFavourites()
    }

    fun getFavourites() {
        viewModelScope.launch {
            getFavouritesUseCase(sessionManager.userId ?: "").collectLatest {
                _uiState.value =
                    FavouritesUiState(
                        favourites = it.map {
                            Favourite(
                                artistName = it.artistName,
                                trackName = it.trackName,
                            )
                        },
                    )
            }
        }
    }

    fun onFavouriteClick(favourite: Favourite) {
        viewModelScope.launch(Dispatchers.Default) {
            manageFavouritesUseCase.invoke(
                userId = sessionManager.userId!!,
                favourite = favourite,
            ).collect { favourites ->
                Timber.d("Favourites updated: $favourites")
                _uiState.value =
                    FavouritesUiState(
                        favourites = favourites.map {
                            Favourite(
                                artistName = it.artistName,
                                trackName = it.trackName,
                            )
                        },
                    )
            }
        }
    }
}
