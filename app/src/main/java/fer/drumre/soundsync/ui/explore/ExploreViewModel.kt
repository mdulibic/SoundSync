package fer.drumre.soundsync.ui.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fer.drumre.soundsync.domain.SessionManager
import fer.drumre.soundsync.domain.usecase.GetFavouritesUseCase
import fer.drumre.soundsync.domain.usecase.explore.GetGeoTopTracksUseCase
import fer.drumre.soundsync.domain.usecase.explore.GetRecommendationsFavorites
import fer.drumre.soundsync.domain.usecase.favourites.ManageFavouritesUseCase
import fer.drumre.soundsync.ui.explore.mapper.ExploreMapper
import fer.drumre.soundsync.ui.explore.model.ExploreInput
import fer.drumre.soundsync.ui.explore.model.ExploreUiState
import fer.drumre.soundsync.ui.favourites.model.FavouritesUiState
import fer.drumre.soundsync.ui.home.model.Favourite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val getGeoTopTracksUseCase: GetGeoTopTracksUseCase,
    private val getFavouritesUseCase: GetFavouritesUseCase,
    private val manageFavouritesUseCase: ManageFavouritesUseCase,
    private val getRecommendationsFavorites: GetRecommendationsFavorites,
    private val exploreMapper: ExploreMapper,
) : ViewModel() {

    private val _uiState = MutableStateFlow<ExploreUiState?>(null)
    val uiState: StateFlow<ExploreUiState?> = _uiState

    val initial: String = sessionManager.userName?.get(0).toString()

    fun signOut() {
        sessionManager.isLoggedIn = false
        sessionManager.userName = null
    }

    init {
        initExplore()
    }

    private fun initExplore() {
        viewModelScope.launch {
            combine(
                getGeoTopTracksUseCase(country = sessionManager.country ?: "Croatia"),
                getFavouritesUseCase(userId = sessionManager.userId ?: ""),
                getRecommendationsFavorites(userId = sessionManager.userId ?: ""),
                ::ExploreInput,
            ).map { (geoTopTracks, favourites, recommendationsFavorites) ->
                exploreMapper.mapToUiState(
                    favourites = favourites,
                    geoTopTracks = geoTopTracks,
                    recommendationsFavorites = recommendationsFavorites,
                )
            }.collectLatest {
                _uiState.value = it
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
                _uiState.value = uiState.value?.copy(
                    favouritesUiState = FavouritesUiState(
                        favourites = favourites.map {
                            Favourite(
                                artistName = it.artistName,
                                trackName = it.trackName,
                            )
                        },
                    ),
                )
            }
        }
    }

    fun onResume() {
        initExplore()
    }
}
