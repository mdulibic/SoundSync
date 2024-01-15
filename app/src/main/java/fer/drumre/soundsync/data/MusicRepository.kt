package fer.drumre.soundsync.data

import fer.drumre.soundsync.data.model.ApiArtist
import fer.drumre.soundsync.data.model.ApiGenre
import fer.drumre.soundsync.data.model.ApiFavourite
import fer.drumre.soundsync.data.model.ApiTrack
import fer.drumre.soundsync.ui.home.model.Favourite
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Path

interface MusicRepository {
    fun fetchGenres(): Flow<List<ApiGenre>>

    fun fetchArtists(): Flow<List<ApiArtist>>

    fun fetchFavourites(userId: String): Flow<List<ApiFavourite>>

    suspend fun manageFavourites(userId: String, favourite: Favourite): Flow<List<ApiFavourite>>

    fun fetchTop50Tracks(): Flow<List<ApiTrack>>

    fun getGeoTopTracks(country: String): Flow<List<ApiTrack>>

    fun getRecommendationsFavorites(userId: String): Flow<Map<String, List<ApiTrack>>>

    fun getRecommendationsFollowees(@Path("userId") userId: String): Flow<Map<String, List<ApiTrack>>>
}
