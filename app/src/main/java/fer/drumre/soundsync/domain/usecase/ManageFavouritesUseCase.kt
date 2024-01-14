package fer.drumre.soundsync.domain.usecase

import fer.drumre.soundsync.data.MusicRepository
import fer.drumre.soundsync.data.model.ApiFavourite
import fer.drumre.soundsync.ui.home.model.Favourite
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ManageFavouritesUseCase @Inject constructor(private val musicRepository: MusicRepository) {
    suspend operator fun invoke(userId: String, favourite: Favourite): Flow<List<ApiFavourite>> {
        return musicRepository.manageFavourites(userId, favourite)
    }
}
