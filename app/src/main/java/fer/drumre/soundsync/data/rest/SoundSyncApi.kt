package fer.drumre.soundsync.data.rest

import fer.drumre.soundsync.data.model.Artist
import fer.drumre.soundsync.data.model.Genre
import fer.drumre.soundsync.data.model.UserInfo
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SoundSyncApi {
    @POST("/users/saveUser")
    suspend fun saveUser(@Body userInfo: UserInfo)

    @GET("/music/genres")
    suspend fun fetchGenres(): List<Genre>

    @GET("/music/artists")
    suspend fun fetchArtists(): List<Artist>
}
