package fer.drumre.soundsync.ui.explore.model

import fer.drumre.soundsync.ui.favourites.model.FavouritesUiState

data class ExploreUiState(
    val favouritesUiState: FavouritesUiState,
    val geoTopTracksUiState: GeoTopTracksUiState,
    val recommendationsFavorites: RecommendationsFavoritesUiState
)
