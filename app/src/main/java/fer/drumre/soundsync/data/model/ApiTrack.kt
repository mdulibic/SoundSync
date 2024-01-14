package fer.drumre.soundsync.data.model

import com.squareup.moshi.Json

data class ApiTrack(
    @Json(name = "_id") val id: String?,
    val name: String,
    val artist: String?,
    val imageUrl: String,
)
