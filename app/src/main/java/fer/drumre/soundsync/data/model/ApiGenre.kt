package fer.drumre.soundsync.data.model

import com.squareup.moshi.Json

data class ApiGenre(
    @Json(name = "_id") val id: String,
    val name: String
)
