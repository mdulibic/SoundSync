package fer.drumre.soundsync.data.model

import com.squareup.moshi.Json

data class ApiUser(
    @Json(name = "_id") val id: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "surname")
    val surname: String,
    @Json(name = "email")
    val email: String,
)
