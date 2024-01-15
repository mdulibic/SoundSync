package fer.drumre.soundsync.ui.explore.model

import fer.drumre.soundsync.data.model.ApiTrack

data class RecommendationsFolloweesUiState(
    val recommendationsFollowees: Map<String, List<ApiTrack>>
)
