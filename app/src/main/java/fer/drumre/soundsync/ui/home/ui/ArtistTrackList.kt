package fer.drumre.soundsync.ui.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import fer.drumre.soundsync.ui.home.model.Favourite
import fer.drumre.soundsync.ui.favourites.model.FavouritesUiState
import fer.drumre.soundsync.ui.home.model.TrackUiState
import timber.log.Timber

@Composable
fun ArtistTrackList(
    tracks: List<TrackUiState>,
    favouritesUiState: FavouritesUiState,
    onFavouriteClick: (Favourite) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        tracks.take(n = 5).forEach {
            TrackItem(
                isFavourite = favouritesUiState.favourites.find { favourite ->
                    favourite.trackName == it.track.name && favourite.artistName == it.artistName
                } != null,
                trackUiState = it,
                onFavouriteClick = onFavouriteClick,
            )
        }
    }
}
