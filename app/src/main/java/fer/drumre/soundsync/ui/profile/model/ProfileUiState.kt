package fer.drumre.soundsync.ui.profile.model

import fer.drumre.soundsync.data.model.ApiUser

data class ProfileUiState(
    val followingList: List<ApiUser>,
    val nonFollowingList: List<ApiUser>,
    val user: ApiUser,
)
