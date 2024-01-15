package fer.drumre.soundsync.data

import fer.drumre.soundsync.data.model.ApiUser
import fer.drumre.soundsync.data.model.FollowRequest
import fer.drumre.soundsync.data.model.SaveUserRequest
import fer.drumre.soundsync.data.model.SaveUserResponse
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun saveUser(userInfo: SaveUserRequest): SaveUserResponse
    fun getNonFollowers(userId: String): Flow<List<ApiUser>>

    fun getFollowingUsers(userId: String): Flow<List<ApiUser>>

    fun getUserById(userId: String): Flow<ApiUser>

    suspend fun followUser(followRequest: FollowRequest)
}
