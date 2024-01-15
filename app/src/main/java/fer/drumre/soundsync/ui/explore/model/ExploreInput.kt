package fer.drumre.soundsync.ui.explore.model

import fer.drumre.soundsync.data.model.ApiFavourite
import fer.drumre.soundsync.data.model.ApiTrack

data class ExploreInput(
    val geoTopTracks: List<ApiTrack>,
    val favourites: List<ApiFavourite>,
    val recommendationsFavorites: Map<String, List<ApiTrack>>
)
