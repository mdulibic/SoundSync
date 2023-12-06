package fer.drumre.soundsync.data

import fer.drumre.soundsync.data.model.UserInfo

interface UserRepository {
    suspend fun saveUser(userInfo: UserInfo)
}
