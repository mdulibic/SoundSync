package fer.drumre.soundsync.ui.home.model

import fer.drumre.soundsync.data.model.ApiTrack

data class TrackUiState(
    val artistName: String,
    val track: ApiTrack,
)
