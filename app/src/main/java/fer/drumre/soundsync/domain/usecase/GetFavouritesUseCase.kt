package fer.drumre.soundsync.domain.usecase

import fer.drumre.soundsync.data.MusicRepository
import fer.drumre.soundsync.data.model.ApiFavourite
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavouritesUseCase @Inject constructor(private val musicRepository: MusicRepository) {
    operator fun invoke(userId: String): Flow<List<ApiFavourite>> =
        musicRepository.fetchFavourites(userId)
}
