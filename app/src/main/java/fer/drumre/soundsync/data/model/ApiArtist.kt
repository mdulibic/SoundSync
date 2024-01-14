package fer.drumre.soundsync.data.model

data class ApiArtist(
    val name: String,
    val imageUrl: String,
    val genre: String,
    val tracks: List<ApiTrack>,
)
