package fer.drumre.soundsync.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fer.drumre.soundsync.data.model.UserInfo
import fer.drumre.soundsync.domain.SessionManager
import fer.drumre.soundsync.domain.usecase.SaveUserUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val saveUserUseCase: SaveUserUseCase,
) : ViewModel() {

    fun saveUser(userInfo: UserInfo) {
        sessionManager.isLoggedIn = true
        sessionManager.userName = userInfo.name
        viewModelScope.launch {
            saveUserUseCase(userInfo = userInfo)
        }
    }
}
