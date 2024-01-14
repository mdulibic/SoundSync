package fer.drumre.soundsync.ui.home.mapper

import fer.drumre.soundsync.data.model.ApiArtist
import fer.drumre.soundsync.data.model.ApiFavourite
import fer.drumre.soundsync.data.model.ApiGenre
import fer.drumre.soundsync.data.model.ApiTrack
import fer.drumre.soundsync.ui.favourites.model.FavouritesUiState
import fer.drumre.soundsync.ui.home.model.ArtistUiState
import fer.drumre.soundsync.ui.home.model.Favourite
import fer.drumre.soundsync.ui.home.model.GenreUiState
import fer.drumre.soundsync.ui.home.model.HomeUiState
import fer.drumre.soundsync.ui.home.model.TagsToExploreUiState
import fer.drumre.soundsync.ui.home.model.Top50TracksUiState
import fer.drumre.soundsync.ui.home.model.TrackUiState
import timber.log.Timber
import javax.inject.Inject

class HomeMapper @Inject constructor() {

    private var startGenreName: String? = null
    private var featuredArtistName: String? = null

    fun mapToUiState(
        artists: List<ApiArtist>,
        genres: List<ApiGenre>,
        favourites: List<ApiFavourite>,
        top50Tracks: List<ApiTrack>,
        updatedStartGenreName: String? = null,
        updatedFeaturedArtistName: String? = null,
    ): HomeUiState {
        updatedStartGenreName?.let {
            startGenreName = it
        }
        updatedFeaturedArtistName?.let {
            featuredArtistName = it
        }

        val tagsToExploreUiState =
            mapToTagsToExploreUiState(artists, genres)

        val favouritesUiState = FavouritesUiState(
            favourites = favourites.map {
                Favourite(
                    artistName = it.artistName,
                    trackName = it.trackName,
                )
            },
        )
        return HomeUiState(
            tagsToExploreUiState = tagsToExploreUiState,
            favouritesUiState = favouritesUiState,
            top50TracksUiState = Top50TracksUiState(
                tracksList = top50Tracks.map {
                    it.mapToTrackUiState()
                },
            ),
        )
    }

    private fun ApiTrack.mapToTrackUiState(): TrackUiState =
        TrackUiState(
            artistName = artist.orEmpty(),
            track = this,
        )

    private fun mapToTagsToExploreUiState(
        artists: List<ApiArtist>,
        genres: List<ApiGenre>,
    ):
        TagsToExploreUiState {
        val genresUiState = genres.map { genre ->
            val artistList = artists.filter { artist -> artist.genre == genre.name }.take(10).map {
                ArtistUiState(
                    name = it.name,
                    imageUrl = it.imageUrl,
                    tracks = it.tracks.map { track ->
                        track.toTrackUiState(
                            artistName = it.name,
                        )
                    },
                )
            }
            val featuredArtist =
                artistList.find { it.name == featuredArtistName } ?: run {
                    val featured = artistList.random()
                    featured
                }
            GenreUiState(
                name = genre.name,
                description = getDescription(genre = genre.name),
                artists = artistList,
                featuredArtist = featuredArtist,
            )
        }

        val startGenre = genresUiState.find { it.name == startGenreName } ?: run {
            val start = genresUiState.random()
            startGenreName = start.name
            start
        }

        val filteredGenresList = genresUiState.filterNot { it.name == startGenre.name }

        return TagsToExploreUiState(
            startGenre = startGenre,
            genres = filteredGenresList,
        )
    }

    private fun ApiTrack.toTrackUiState(
        artistName: String,
    ): TrackUiState {
        Timber.d("ApiTrack.toTrackUiState ${this.name} $artistName")
        return TrackUiState(
            track = this,
            artistName = artistName,
        )
    }

    private fun getDescription(genre: String): String {
        return when (genre) {
            "Pop" -> "Embark on a melodic journey through the vibrant and ever-changing landscape of popular music. Pop transcends generations, capturing the essence of the zeitgeist with its infectious beats and catchy melodies. A symphony of emotions awaits, from heartwarming ballads to dance-worthy anthems."

            "Rap/Hip Hop" -> "Immerse yourself in the raw and unfiltered expressions of urban poetry. Rap/Hip Hop, a revolutionary art form, fuses rhythmic speech with dynamic beats, serving as a powerful vehicle for storytelling and social commentary. Explore the intricate rhymes and beats that echo the heartbeat of the streets."

            "Rock" -> "Venture into the electrifying world of Rock, where the soulful wails of guitars collide with thunderous rhythms. Born from rebellion, this genre embraces diversity, offering everything from soul-stirring ballads to head-banging anthems. Let the sonic waves of Rock carry you through a musical odyssey."

            "Dance" -> "Surrender to the pulsating beats and infectious energy of Dance music. Designed to move both body and soul, this genre invites you to lose yourself in rhythmic euphoria. From disco to electronic beats, Dance music is an invitation to unleash your inner dancer and celebrate life."

            "R&B" -> "Journey into the soulful realm of Rhythm and Blues, where emotive vocals and soul-stirring melodies intertwine. R&B, a genre rooted in the rich tapestry of African American music, invites you to explore love, heartbreak, and resilience through its timeless tunes."

            "Alternativna glazba" -> "Embark on an avant-garde exploration through the kaleidoscopic soundscape of Alternativna glazba. Rejecting musical conventions, this genre invites you to challenge your sonic palate with eclectic rhythms and unconventional harmonies. Enter a realm where the unexpected is celebrated."

            "Elektronička glazba" -> "Dive into the mesmerizing world of Elektronička glazba, where sonic landscapes are crafted through electronic alchemy. From ambient soundscapes to pulsating beats, this genre invites you to explore the limitless possibilities of synthetic sound. Immerse yourself in a symphony of electrons."

            "Folk" -> "Embark on a nostalgic journey through the traditions of Folk music. Rooted in cultural narratives and passed down through generations, Folk invites you to connect with the heartbeat of communities. From rustic ballads to lively jigs, Folk music is a timeless celebration of human stories."

            "Reggae" -> "Feel the rhythmic vibrations and positive vibrations of Reggae music. Originating from the soulful sounds of Jamaica, Reggae invites you to sway to its laid-back rhythms and embrace messages of love, unity, and social consciousness. Let the spirit of the islands captivate your soul."

            "Džez" -> "Immerse yourself in the improvisational brilliance of Jazz, where melodies and rhythms intertwine in a captivating dance. Jazz, a genre that defies boundaries, invites you to explore the intricate interplay of instruments and embrace the freedom of musical expression."

            "Klasična glazba" -> "Embark on a transcendent journey through the hallowed halls of Classical Music. A timeless and structured art form, Klasična Glazba invites you to experience the emotional depth and intellectual complexity of compositions that have withstood the test of time."

            "Filmovi/Igrice" -> "Step into the enchanting world of Film and Game Music, where orchestral arrangements and electronic wizardry create immersive sonic landscapes. Whether accompanying epic movie scenes or guiding your virtual adventures, this genre transports you to realms of imagination."

            "Metal" -> "Plunge into the raw and thunderous realm of Metal, where distorted guitars and thunderous drums create a sonic tempest. From blistering solos to guttural vocals, Metal invites you to embrace the intensity and diversity within this genre. Brace yourself for a headbanging journey."

            "Soul & Funk" -> "Savor the soulful grooves and infectious rhythms of Soul & Funk. Rooted in the rich musical traditions of African American culture, this genre invites you to get down to the funk and bask in the emotive richness of soulful vocals and dynamic instrumentation."

            "Afrička Glazba" -> "Embark on a rhythmic safari through the vibrant sounds of Afrička Glazba. From the beating heart of the continent, this genre celebrates the rich tapestry of African cultures, inviting you to dance to the rhythms of life and connect with the ancestral beats."

            "Azijska Glazba" -> "Embark on a sonic journey across the diverse landscapes of Asian Music. From the mystical tunes of the East to the energetic beats of the Far East, Azijska Glazba invites you to explore the richness and diversity of musical traditions from the world's largest continent."

            "Blues" -> "Surrender to the melancholic beauty of Blues, where heartfelt lyrics and soulful guitar licks intertwine. Born from the struggles and triumphs of African American history, Blues invites you to feel the emotional depth and authenticity that defines this timeless genre."

            "Brazilska glazba" -> "Sway to the seductive rhythms of Brazilska glazba, where the sounds of samba and bossa nova capture the essence of Brazilian culture. From the lively beats of Carnival to the poetic melodies of bossa nova, this genre is an invitation to experience the vibrant soul of Brazil."

            "Dječje" -> "Enter the whimsical world of Dječje glazba, where melodies are crafted to enchant the youngest of hearts. From playful tunes to educational anthems, this genre invites children to explore the joy of music and learning in a magical and imaginative way."

            "Indijska Glazba" -> "Embark on a spiritual and sonic odyssey through the intricate melodies and rhythmic tapestries of Indijska Glazba. Rooted in ancient traditions, this genre invites you to experience the diverse"
            "Latino" -> "Indulge in the vibrant rhythms and passionate melodies of Latino music, where the soulful spirit of Latin America comes alive. From the infectious beats of salsa to the sensual sway of tango, Latino music is a celebration of life, love, and the rich cultural tapestry of the region. Immerse yourself in the sizzling energy of mariachi trumpets, the rhythmic allure of reggaeton, and the timeless romance of bolero."
            else -> ""
        }
    }
}
