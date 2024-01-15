package fer.drumre.soundsync.domain.usecase.user

import fer.drumre.soundsync.data.UserRepository
import fer.drumre.soundsync.data.model.FollowRequest
import timber.log.Timber
import javax.inject.Inject

class FollowUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(followRequest: FollowRequest) {
        Timber.d("FollowUserUseCase: $followRequest")
        userRepository.followUser(followRequest)
    }
}
