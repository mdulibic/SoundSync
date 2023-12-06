package fer.drumre.soundsync.data.model

import com.squareup.moshi.Json

data class UserInfo(
    @Json(name = "name")
    val name: String,
    @Json(name = "surname")
    val surname: String,
    @Json(name = "email")
    val email: String,
)
