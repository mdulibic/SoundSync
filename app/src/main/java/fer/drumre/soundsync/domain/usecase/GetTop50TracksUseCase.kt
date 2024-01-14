package fer.drumre.soundsync.domain.usecase

import fer.drumre.soundsync.data.MusicRepository
import fer.drumre.soundsync.data.model.ApiTrack
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTop50TracksUseCase @Inject constructor(private val musicRepository: MusicRepository) {
    operator fun invoke(): Flow<List<ApiTrack>> {
        return musicRepository.fetchTop50Tracks()
    }
}

