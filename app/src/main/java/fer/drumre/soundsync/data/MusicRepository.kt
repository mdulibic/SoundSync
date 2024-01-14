package fer.drumre.soundsync.data

import fer.drumre.soundsync.data.model.ApiArtist
import fer.drumre.soundsync.data.model.ApiGenre
import fer.drumre.soundsync.data.model.ApiFavourite
import fer.drumre.soundsync.ui.home.model.Favourite
import kotlinx.coroutines.flow.Flow

interface MusicRepository {
    fun fetchGenres(): Flow<List<ApiGenre>>

    fun fetchArtists(): Flow<List<ApiArtist>>

    fun fetchFavourites(userId: String): Flow<List<ApiFavourite>>

    suspend fun manageFavourites(userId: String, favourite: Favourite): Flow<List<ApiFavourite>>
}
