package fer.drumre.soundsync.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fer.drumre.soundsync.data.model.ApiArtist
import fer.drumre.soundsync.data.model.ApiFavourite
import fer.drumre.soundsync.data.model.ApiGenre
import fer.drumre.soundsync.data.model.ApiTrack
import fer.drumre.soundsync.domain.SessionManager
import fer.drumre.soundsync.domain.usecase.GetArtistsUseCase
import fer.drumre.soundsync.domain.usecase.GetFavouritesUseCase
import fer.drumre.soundsync.domain.usecase.GetGenresUseCase
import fer.drumre.soundsync.domain.usecase.GetTop50TracksUseCase
import fer.drumre.soundsync.domain.usecase.ManageFavouritesUseCase
import fer.drumre.soundsync.ui.favourites.model.FavouritesUiState
import fer.drumre.soundsync.ui.home.mapper.HomeMapper
import fer.drumre.soundsync.ui.home.model.Favourite
import fer.drumre.soundsync.ui.home.model.HomeInput
import fer.drumre.soundsync.ui.home.model.HomeUiState
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
class HomeViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val getArtistsUseCase: GetArtistsUseCase,
    private val getGenresUseCase: GetGenresUseCase,
    private val getFavouritesUseCase: GetFavouritesUseCase,
    private val manageFavouritesUseCase: ManageFavouritesUseCase,
    private val getTop50TracksUseCase: GetTop50TracksUseCase,
    private val homeMapper: HomeMapper,
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState?>(null)
    val uiState: StateFlow<HomeUiState?> = _uiState

    val initial: String = sessionManager.userName?.get(0).toString()

    private var artistsList: List<ApiArtist>? = null

    private var genresList: List<ApiGenre>? = null

    private var favouritesList: List<ApiFavourite>? = null

    private var top50TracksList: List<ApiTrack>? = null

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
                getFavouritesUseCase(sessionManager.userId ?: ""),
                getTop50TracksUseCase(),
                ::HomeInput,
            ).map { (artists, genres, favourites, top50Tracks) ->
                artistsList = artists
                genresList = genres
                favouritesList = favourites
                top50TracksList = top50Tracks
                homeMapper.mapToUiState(
                    artists = artists,
                    genres = genres,
                    favourites = favourites,
                    top50Tracks = top50Tracks,
                )
            }.collectLatest {
                _uiState.value = it
            }
        }
    }

    fun onGenreClick(genreName: String) {
        val uiState = homeMapper.mapToUiState(
            artists = artistsList ?: emptyList(),
            genres = genresList ?: emptyList(),
            favourites = favouritesList ?: emptyList(),
            updatedStartGenreName = genreName,
            top50Tracks = top50TracksList ?: emptyList(),
        )
        _uiState.value = uiState
    }

    fun onArtistClick(artistName: String) {
        val uiState = homeMapper.mapToUiState(
            artists = artistsList ?: emptyList(),
            genres = genresList ?: emptyList(),
            favourites = favouritesList ?: emptyList(),
            updatedFeaturedArtistName = artistName,
            top50Tracks = top50TracksList ?: emptyList(),
        )
        _uiState.value = uiState
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
        getHomeData()
    }
}
