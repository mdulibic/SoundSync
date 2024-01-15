package fer.drumre.soundsync.ui.explore.mapper

import fer.drumre.soundsync.data.model.ApiFavourite
import fer.drumre.soundsync.data.model.ApiTrack
import fer.drumre.soundsync.ui.explore.model.ExploreUiState
import fer.drumre.soundsync.ui.explore.model.GeoTopTracksUiState
import fer.drumre.soundsync.ui.explore.model.RecommendationsFavoritesUiState
import fer.drumre.soundsync.ui.favourites.model.FavouritesUiState
import fer.drumre.soundsync.ui.home.model.Favourite
import fer.drumre.soundsync.ui.home.model.TrackUiState
import javax.inject.Inject

class ExploreMapper @Inject constructor() {
    fun mapToUiState(
        favourites: List<ApiFavourite>,
        geoTopTracks: List<ApiTrack>,
        recommendationsFavorites: Map<String, List<ApiTrack>>,
    ): ExploreUiState {
        val favouritesUiState = FavouritesUiState(
            favourites = favourites.map {
                Favourite(
                    artistName = it.artistName,
                    trackName = it.trackName,
                )
            },
        )
        val geoTopTracksUiState = GeoTopTracksUiState(
            tracksList = geoTopTracks.map {
                it.mapToTrackUiState()
            },
        )

        val recommendationsFavorites = RecommendationsFavoritesUiState(
            recommendationsFavorites = recommendationsFavorites,
        )

        return ExploreUiState(
            favouritesUiState = favouritesUiState,
            geoTopTracksUiState = geoTopTracksUiState,
            recommendationsFavorites = recommendationsFavorites,
        )
    }

    fun ApiTrack.mapToTrackUiState(): TrackUiState =
        TrackUiState(
            artistName = artist.orEmpty(),
            track = this,
        )
}
