package fer.drumre.soundsync.domain.usecase

import fer.drumre.soundsync.data.UserRepository
import fer.drumre.soundsync.data.model.SaveUserRequest
import fer.drumre.soundsync.data.model.SaveUserResponse
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(userInfo: SaveUserRequest): SaveUserResponse =
        userRepository.saveUser(userInfo = userInfo)
}
