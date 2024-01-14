package fer.drumre.soundsync.ui.home.model

import fer.drumre.soundsync.data.model.ApiArtist
import fer.drumre.soundsync.data.model.ApiFavourite
import fer.drumre.soundsync.data.model.ApiGenre
import fer.drumre.soundsync.data.model.ApiTrack

data class HomeInput(
    val artists: List<ApiArtist>,
    val genres: List<ApiGenre>,
    val favourites: List<ApiFavourite>,
    val top50Tracks: List<ApiTrack>,
)
