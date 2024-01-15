package fer.drumre.soundsync.ui.explore.model

import fer.drumre.soundsync.data.model.ApiTrack

data class RecommendationsFavoritesUiState(
    val recommendationsFavorites: Map<String, List<ApiTrack>>
)
