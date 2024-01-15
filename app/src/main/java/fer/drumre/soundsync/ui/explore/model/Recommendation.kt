package fer.drumre.soundsync.ui.explore.model

import fer.drumre.soundsync.data.model.ApiTrack

data class Recommendation(
    val artistName: String,
    val tracks: List<ApiTrack>,
)
