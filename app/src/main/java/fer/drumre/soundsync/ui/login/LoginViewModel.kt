package fer.drumre.soundsync.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.facebook.Profile
import com.facebook.ProfileTracker
import dagger.hilt.android.lifecycle.HiltViewModel
import fer.drumre.soundsync.data.model.SaveUserRequest
import fer.drumre.soundsync.domain.SessionManager
import fer.drumre.soundsync.domain.usecase.user.SaveUserUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val saveUserUseCase: SaveUserUseCase,
) : ViewModel() {

    private val _navigateToHome = MutableSharedFlow<Unit>()
    val navigateToHome: SharedFlow<Unit> = _navigateToHome

    private val profileTracker =
        object : ProfileTracker() {
            override fun onCurrentProfileChanged(oldProfile: Profile?, currentProfile: Profile?) {
                Timber.d("Current profile ${currentProfile?.name}")
                if (currentProfile != null) {
                    saveUser(
                        userInfo = SaveUserRequest(
                            name = currentProfile.firstName.toString(),
                            surname = currentProfile.lastName.toString(),
                            email = "${currentProfile.firstName?.lowercase()}.${currentProfile.lastName?.lowercase()}@gmail.com",
                        ),
                    )
                }
            }
        }

    fun checkFbProfile(): Boolean {
        return Profile.getCurrentProfile()?.let {
            saveUser(
                userInfo = SaveUserRequest(
                    name = it.firstName.toString(),
                    surname = it.lastName.toString(),
                    email = "${it.firstName?.lowercase()}.${it.lastName?.lowercase()}@gmail.com",
                ),
            )
            navigateToHome()
            true
        } ?: run {
            false
        }
    }

    private fun navigateToHome() {
        viewModelScope.launch {
            _navigateToHome.emit(Unit)
        }
    }

    fun saveUser(userInfo: SaveUserRequest) {
        sessionManager.isLoggedIn = true
        sessionManager.userName = userInfo.name
        viewModelScope.launch {
            val response = saveUserUseCase(userInfo = userInfo)
            sessionManager.userId = response.user.id
        }
    }

    override fun onCleared() {
        profileTracker.stopTracking()
        super.onCleared()
    }
}
