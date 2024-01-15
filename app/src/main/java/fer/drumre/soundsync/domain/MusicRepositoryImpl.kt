package fer.drumre.soundsync.domain

import fer.drumre.soundsync.data.MusicRepository
import fer.drumre.soundsync.data.model.ApiArtist
import fer.drumre.soundsync.data.model.ApiFavourite
import fer.drumre.soundsync.data.model.ApiGenre
import fer.drumre.soundsync.data.model.ApiTrack
import fer.drumre.soundsync.data.rest.SoundSyncApi
import fer.drumre.soundsync.ui.home.model.Favourite
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class MusicRepositoryImpl @Inject constructor(private val api: SoundSyncApi) : MusicRepository {

    override fun fetchGenres(): Flow<List<ApiGenre>> = flow {
        try {
            emit(api.fetchGenres())
        } catch (e: Exception) {
            handleException(e, "fetchGenres")
        }
    }

    override fun fetchArtists(): Flow<List<ApiArtist>> = flow {
        try {
            emit(api.fetchArtists())
        } catch (e: Exception) {
            handleException(e, "fetchArtists")
        }
    }

    override fun fetchFavourites(userId: String): Flow<List<ApiFavourite>> = flow {
        try {
            emit(api.fetchFavourites(userId))
        } catch (e: Exception) {
            handleException(e, "fetchFavourites")
        }
    }

    override suspend fun manageFavourites(
        userId: String,
        favourite: Favourite,
    ): Flow<List<ApiFavourite>> = flow {
        try {
            emit(api.manageFavourite(userId, favourite))
        } catch (e: Exception) {
            handleException(e, "manageFavourites")
        }
    }

    override fun fetchTop50Tracks(): Flow<List<ApiTrack>> = flow {
        try {
            emit(api.fetchTop50Tracks())
        } catch (e: Exception) {
            handleException(e, "fetchTop50Tracks")
        }
    }

    override fun getGeoTopTracks(country: String): Flow<List<ApiTrack>> = flow {
        try {
            emit(api.getGeoTopTracks(country))
        } catch (e: Exception) {
            handleException(e, "getGeoTopTracks")
        }
    }

    override fun getRecommendationsFavorites(userId: String): Flow<Map<String, List<ApiTrack>>> =
        flow {
            try {
                emit(api.getRecommendationsFavorites(userId))
            } catch (e: Exception) {
                handleException(e, "getRecommendationsFavorites")
            }
        }

    override fun getRecommendationsFollowees(userId: String): Flow<Map<String, List<ApiTrack>>> =
        flow {
            try {
                emit(api.getRecommendationsFollowees(userId = userId))
            } catch (e: Exception) {
                handleException(e, "getRecommendationsFollowees")
            }
        }

    private fun handleException(exception: Exception, method: String) {
        val errorMessage = exception.message ?: "Unknown error"
        val causeMessage = exception.cause?.message ?: "Unknown cause"
        Timber.e("SoundSyncApi - $method: $errorMessage. Cause: $causeMessage")
    }
}
