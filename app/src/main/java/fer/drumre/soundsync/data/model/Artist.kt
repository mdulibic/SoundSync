package fer.drumre.soundsync.data.model

data class Artist(
    val name: String,
    val imageUrl: String,
    val genre: String,
    val tracks: List<Track>,
)
