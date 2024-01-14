package fer.drumre.soundsync.data.model

import com.squareup.moshi.Json

data class ApiFavourite(
    @Json(name = "_id") val id: String?,
    val userId: String,
    val artistName: String,
    val trackName: String,
)
