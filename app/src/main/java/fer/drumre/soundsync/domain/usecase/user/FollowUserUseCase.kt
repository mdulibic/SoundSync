package fer.drumre.soundsync.domain.usecase.user

import fer.drumre.soundsync.data.UserRepository
import fer.drumre.soundsync.data.model.FollowRequest
import javax.inject.Inject

class FollowUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(followRequest: FollowRequest) {
        userRepository.followUser(followRequest)
    }
}
