package fer.drumre.soundsync.ui.home.model

data class ArtistUiState(
    val name: String,
    val imageUrl: String,
    val tracks: List<TrackUiState>,
)
