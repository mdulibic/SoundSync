package fer.drumre.soundsync.data

import fer.drumre.soundsync.data.model.SaveUserRequest
import fer.drumre.soundsync.data.model.SaveUserResponse

interface UserRepository {
    suspend fun saveUser(userInfo: SaveUserRequest): SaveUserResponse
}
