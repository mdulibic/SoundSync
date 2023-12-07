package fer.drumre.soundsync.ui.home.model

import fer.drumre.soundsync.data.model.Artist

data class ArtistsByGenreUiState(
    val rockArtists: List<Artist>,
    val popArtists: List<Artist>,
)
