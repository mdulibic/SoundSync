package fer.drumre.soundsync.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.facebook.Profile
import com.facebook.ProfileTracker
import dagger.hilt.android.lifecycle.HiltViewModel
import fer.drumre.soundsync.data.model.SaveUserRequest
import fer.drumre.soundsync.domain.SessionManager
import fer.drumre.soundsync.domain.usecase.SaveUserUseCase
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
                    navigateToHome()
                }
            }
        }

    fun checkFbProfile() {
        Profile.getCurrentProfile()?.apply {
            saveUser(
                userInfo = SaveUserRequest(
                    name = firstName.toString(),
                    surname = lastName.toString(),
                    email = "${firstName?.lowercase()}.${lastName?.lowercase()}@gmail.com",
                ),
            )
            navigateToHome()
        }
    }

    fun navigateToHome() {
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

//    fun handleFacebookLoginResult(result: LoginResult) {
//        val request = GraphRequest.newMeRequest(
//            result.accessToken,
//        ) { jsonObject, response ->
//            if (jsonObject != null) {
//                val userId = jsonObject.getString("id")
//                val userName = jsonObject.getString("name")
//                val email = jsonObject.getString("email")
//
//                val fullName = userName.split(" ")
//                val firstName = fullName.firstOrNull() ?: ""
//                val lastName = fullName.drop(1).joinToString(separator = " ")
//
//                Timber.d("First Name: $firstName, Last Name: $lastName")
//                saveUser(
//                    userInfo = UserInfo(
//                        name = firstName,
//                        surname = lastName,
//                        email = email,
//                    ),
//                )
//                viewModelScope.launch {
//                    _navigateToHome.emit(Unit)
//                }
//            }
//        }
//
//        val parameters = Bundle()
//        parameters.putString("fields", "id,name,email")
//        request.parameters = parameters
//        request.executeAsync()
//    }
}
