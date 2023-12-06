package fer.drumre.soundsync.domain.usecase

import fer.drumre.soundsync.data.UserRepository
import fer.drumre.soundsync.data.model.UserInfo
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(userInfo: UserInfo) {
        userRepository.saveUser(userInfo = userInfo)
    }
}
