package fer.drumre.soundsync.data

import fer.drumre.soundsync.data.model.Artist
import fer.drumre.soundsync.data.model.Genre
import kotlinx.coroutines.flow.Flow

interface MusicRepository {
    fun fetchGenres(): Flow<List<Genre>>

    fun fetchArtists(): Flow<List<Artist>>
}
