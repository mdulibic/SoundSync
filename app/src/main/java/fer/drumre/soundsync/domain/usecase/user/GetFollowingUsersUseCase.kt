package fer.drumre.soundsync.domain.usecase.user

import fer.drumre.soundsync.data.UserRepository
import fer.drumre.soundsync.data.model.ApiUser
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFollowingUsersUseCase @Inject constructor(private val userRepository: UserRepository) {
    operator fun invoke(userId: String): Flow<List<ApiUser>> =
        userRepository.getFollowingUsers(userId)
}
