package fer.drumre.soundsync.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fer.drumre.soundsync.data.model.FollowRequest
import fer.drumre.soundsync.domain.SessionManager
import fer.drumre.soundsync.domain.usecase.user.FollowUserUseCase
import fer.drumre.soundsync.domain.usecase.user.GetFollowingUsersUseCase
import fer.drumre.soundsync.domain.usecase.user.GetNonFollowingUserUseCase
import fer.drumre.soundsync.domain.usecase.user.GetUserInfoUseCase
import fer.drumre.soundsync.ui.profile.model.ProfileUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val getFollowingUsersUseCase: GetFollowingUsersUseCase,
    private val getNonFollowingUserUseCase: GetNonFollowingUserUseCase,
    private val followUserUseCase: FollowUserUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProfileUiState?>(null)
    val uiState: StateFlow<ProfileUiState?> = _uiState

    private val _navigateToLogin = MutableSharedFlow<Unit>()
    val navigateToLogin: SharedFlow<Unit> = _navigateToLogin

    init {
        getProfileData()
    }

    private fun getProfileData() {
        viewModelScope.launch {
            combine(
                getUserInfoUseCase(sessionManager.userId ?: ""),
                getFollowingUsersUseCase(sessionManager.userId ?: ""),
                getNonFollowingUserUseCase(sessionManager.userId ?: ""),
                ::Triple,
            ).map { (userInfo, following, nonFollowing) ->
                ProfileUiState(
                    followingList = following,
                    nonFollowingList = nonFollowing,
                    user = userInfo,
                )
            }.collectLatest {
                _uiState.value = it
            }
        }
    }

    fun followUser(userId: String) {
        viewModelScope.launch(Dispatchers.Default) {
            followUserUseCase(
                followRequest = FollowRequest(
                    followerId = sessionManager.userId ?: "",
                    followeeId = userId,
                ),
            )
            getProfileData()
        }
    }

    fun onLogoutClick() {
        sessionManager.isLoggedIn = false
        sessionManager.userId = null
        viewModelScope.launch {
            _navigateToLogin.emit(Unit)
        }
    }
}
