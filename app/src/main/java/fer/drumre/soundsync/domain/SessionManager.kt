package fer.drumre.soundsync.domain

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SessionManager @Inject constructor(@ApplicationContext private val appContext: Context) {
    companion object {
        private const val PREFERENCES_APP = "prefs_app"
        private const val LOGGED_IN = "logged_in"
        private const val USER_NAME = "user_name"
        private const val USER_ID = "user_id"
    }

    private val appPreferences: SharedPreferences by lazy {
        appContext.getSharedPreferences(PREFERENCES_APP, Context.MODE_PRIVATE)
    }

    var isLoggedIn: Boolean
        get() = appPreferences.getBoolean(LOGGED_IN, false)
        set(value) {
            appPreferences.edit().putBoolean(LOGGED_IN, value).apply()
        }

    var userName: String?
        get() = appPreferences.getString(USER_NAME, null)
        set(value) {
            appPreferences.edit().putString(USER_NAME, value).apply()
        }

    var userId: String?
        get() = appPreferences.getString(USER_ID, null)
        set(value) {
            appPreferences.edit().putString(USER_ID, value).apply()
        }
}
