package fer.drumre.soundsync.ui.home.mapper

import fer.drumre.soundsync.data.model.Artist
import fer.drumre.soundsync.data.model.Genre
import fer.drumre.soundsync.ui.home.model.ArtistsByGenreUiState
import fer.drumre.soundsync.ui.home.model.GenresUiState
import fer.drumre.soundsync.ui.home.model.HomeUiState
import timber.log.Timber
import javax.inject.Inject

class HomeMapper @Inject constructor() {
    fun mapToUiState(artists: List<Artist>, genres: List<Genre>): HomeUiState {
        val genresUiState = GenresUiState(genres = genres)
        val rockArtists = artists.filter { it.genre == "Svi" }
        val popArtists = artists.filter { it.genre == "Rock" }
        Timber.d("Svi list $rockArtists")
        Timber.d("Pop list $popArtists")
        return HomeUiState(
            artistsUiState = ArtistsByGenreUiState(
                rockArtists = rockArtists,
                popArtists = popArtists,
            ),
            genresUiState = genresUiState,
        )
    }
}
