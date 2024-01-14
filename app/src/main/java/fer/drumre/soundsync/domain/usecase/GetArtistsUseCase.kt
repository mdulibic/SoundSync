package fer.drumre.soundsync.domain.usecase

import fer.drumre.soundsync.data.MusicRepository
import fer.drumre.soundsync.data.model.ApiArtist
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetArtistsUseCase @Inject constructor(private val musicRepository: MusicRepository) {
    operator fun invoke(): Flow<List<ApiArtist>> = musicRepository.fetchArtists()
}
