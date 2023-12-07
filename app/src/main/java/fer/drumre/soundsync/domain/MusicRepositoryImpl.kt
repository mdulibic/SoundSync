package fer.drumre.soundsync.domain

import fer.drumre.soundsync.data.MusicRepository
import fer.drumre.soundsync.data.model.Artist
import fer.drumre.soundsync.data.model.Genre
import fer.drumre.soundsync.data.rest.SoundSyncApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MusicRepositoryImpl @Inject constructor(private val api: SoundSyncApi) : MusicRepository {
    override fun fetchGenres(): Flow<List<Genre>> = flow {
        emit(api.fetchGenres())
    }

    override fun fetchArtists(): Flow<List<Artist>> = flow {
        emit(api.fetchArtists())
    }
}
