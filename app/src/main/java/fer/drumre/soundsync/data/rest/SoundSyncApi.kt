package fer.drumre.soundsync.data.rest

import fer.drumre.soundsync.data.model.UserInfo
import retrofit2.http.Body
import retrofit2.http.POST

interface SoundSyncApi {
    @POST("saveUser")
    suspend fun saveUser(@Body userInfo: UserInfo): Boolean
}
