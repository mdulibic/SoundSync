package fer.drumre.soundsync.domain.usecase

import fer.drumre.soundsync.data.MusicRepository
import fer.drumre.soundsync.data.model.ApiTrack
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGeoTopTracksUseCase @Inject constructor(private val musicRepository: MusicRepository) {
    operator fun invoke(country: String): Flow<List<ApiTrack>> {
        return musicRepository.getGeoTopTracks(country = country)
    }
}
