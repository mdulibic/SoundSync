package fer.drumre.soundsync.ui.home.model

data class GenreUiState(
    val name: String,
    val description: String,
    val featuredArtist: ArtistUiState,
    val artists: List<ArtistUiState>,
)
