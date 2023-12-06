package fer.drumre.soundsync.domain

import fer.drumre.soundsync.data.UserRepository
import fer.drumre.soundsync.data.model.UserInfo
import fer.drumre.soundsync.data.rest.SoundSyncApi
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val api: SoundSyncApi) : UserRepository {
    override suspend fun saveUser(userInfo: UserInfo) {
        //api.saveUser(userInfo = userInfo)
    }
}