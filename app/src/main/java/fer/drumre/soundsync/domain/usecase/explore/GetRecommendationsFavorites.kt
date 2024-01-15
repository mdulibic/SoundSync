package fer.drumre.soundsync.domain.usecase.explore

import fer.drumre.soundsync.data.MusicRepository
import fer.drumre.soundsync.data.model.ApiTrack
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecommendationsFavorites @Inject constructor(private val musicRepository: MusicRepository) {
    operator fun invoke(userId: String): Flow<Map<String, List<ApiTrack>>> {
        return musicRepository.getRecommendationsFavorites(userId = userId)
    }
}
