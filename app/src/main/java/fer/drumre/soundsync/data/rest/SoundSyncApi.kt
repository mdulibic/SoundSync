package fer.drumre.soundsync.data.rest

import fer.drumre.soundsync.data.model.ApiArtist
import fer.drumre.soundsync.data.model.ApiFavourite
import fer.drumre.soundsync.data.model.ApiGenre
import fer.drumre.soundsync.data.model.ApiTrack
import fer.drumre.soundsync.data.model.ApiUser
import fer.drumre.soundsync.data.model.FollowRequest
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

    @GET("/music/geoTopTracks/{country}")
    suspend fun getGeoTopTracks(@Path("country") country: String): List<ApiTrack>

    @GET("/music/{userId}/recommendations_favorites")
    suspend fun getRecommendationsFavorites(@Path("userId") userId: String): Map<String, List<ApiTrack>>

    @GET("/users/{userId}/nonFollowers")
    suspend fun getNonFollowers(@Path("userId") userId: String): List<ApiUser>

    @GET("/users/{userId}/following")
    suspend fun getFollowingUsers(@Path("userId") userId: String): List<ApiUser>

    @GET("/users/{userId}")
    suspend fun getUserById(@Path("userId") userId: String): ApiUser

    @POST("/users/follow")
    suspend fun followUser(@Body followRequest: FollowRequest)

    @GET("/music/{userId}/recommendations_followees")
    suspend fun getRecommendationsFollowees(@Path("userId") userId: String): Map<String, List<ApiTrack>>
}
