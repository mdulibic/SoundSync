package fer.drumre.soundsync.data.rest

import fer.drumre.soundsync.data.model.ApiArtist
import fer.drumre.soundsync.data.model.ApiFavourite
import fer.drumre.soundsync.data.model.ApiGenre
import fer.drumre.soundsync.data.model.ApiTrack
import fer.drumre.soundsync.data.model.SaveUserRequest
import fer.drumre.soundsync.data.model.SaveUserResponse
import fer.drumre.soundsync.ui.home.model.Favourite
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SoundSyncApi {
    @POST("/users/saveUser")
    suspend fun saveUser(@Body userInfo: SaveUserRequest): SaveUserResponse

    @GET("/music/genres")
    suspend fun fetchGenres(): List<ApiGenre>

    @GET("/music/artists")
    suspend fun fetchArtists(): List<ApiArtist>

    @GET("/music/favourites/{userId}")
    suspend fun fetchFavourites(@Path("userId") userId: String): List<ApiFavourite>

    @POST("/music/favourites/{userId}")
    suspend fun manageFavourite(
        @Path("userId") userId: String,
        @Body favourite: Favourite,
    ): List<ApiFavourite>

    @GET("/music/top50")
    suspend fun fetchTop50Tracks(): List<ApiTrack>
}
