package fer.drumre.soundsync.domain.usecase

import fer.drumre.soundsync.data.MusicRepository
import fer.drumre.soundsync.data.model.Genre
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(private val musicRepository: MusicRepository) {
    operator fun invoke(): Flow<List<Genre>> {
        return musicRepository.fetchGenres()
    }
}
