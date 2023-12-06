package fer.drumre.soundsync.domain

import fer.drumre.soundsync.data.MusicRepository
import fer.drumre.soundsync.data.rest.SoundSyncApi
import javax.inject.Inject

class MusicRepositoryImpl @Inject constructor(api: SoundSyncApi) : MusicRepository
