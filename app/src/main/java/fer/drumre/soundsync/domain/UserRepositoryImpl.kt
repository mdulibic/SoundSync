package fer.drumre.soundsync.domain

import fer.drumre.soundsync.data.UserRepository
import fer.drumre.soundsync.data.model.ApiUser
import fer.drumre.soundsync.data.model.FollowRequest
import fer.drumre.soundsync.data.model.SaveUserRequest
import fer.drumre.soundsync.data.model.SaveUserResponse
import fer.drumre.soundsync.data.rest.SoundSyncApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val api: SoundSyncApi) : UserRepository {

    override suspend fun saveUser(userInfo: SaveUserRequest): SaveUserResponse {
        return try {
            api.saveUser(userInfo = userInfo)
        } catch (e: Exception) {
            Timber.e(e, "Error saving user")
            throw e
        }
    }

    override fun getNonFollowers(userId: String): Flow<List<ApiUser>> = flow {
        try {
            emit(api.getNonFollowers(userId))
        } catch (e: Exception) {
            Timber.e(e, "Error getting non-followers")
            emit(emptyList())
        }
    }

    override fun getFollowingUsers(userId: String): Flow<List<ApiUser>> = flow {
        try {
            emit(api.getFollowingUsers(userId))
        } catch (e: Exception) {
            Timber.e(e, "Error getting following users")
            emit(emptyList())
        }
    }

    override fun getUserById(userId: String): Flow<ApiUser> = flow {
        try {
            emit(api.getUserById(userId))
        } catch (e: Exception) {
            Timber.e(e, "Error getting user by ID")
            emit(ApiUser("", "", "", ""))
        }
    }

    override suspend fun followUser(followRequest: FollowRequest) {
        try {
            api.followUser(followRequest)
        } catch (e: Exception) {
            Timber.e(e, "Error following user")
            throw e
        }
    }
}
