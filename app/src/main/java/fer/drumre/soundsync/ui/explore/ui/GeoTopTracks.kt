package fer.drumre.soundsync.ui.explore.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import fer.digobr.kidslingo.theme.AppSecondary
import fer.drumre.soundsync.ui.explore.model.GeoTopTracksUiState
import fer.drumre.soundsync.ui.favourites.model.FavouritesUiState
import fer.drumre.soundsync.ui.home.model.Favourite

@Composable
fun GeoTopTracks(
    geoTopTracksUiState: GeoTopTracksUiState,
    favouritesUiState: FavouritesUiState,
    onFavouriteClick: (Favourite) -> Unit,
) {
    Column {
        Text(
            text = "Based on geolocation",
            color = AppSecondary,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h5,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Divider(
            modifier = Modifier
                .height(4.dp)
                .width(100.dp)
                .background(Color.White),
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(geoTopTracksUiState.tracksList) { track ->
                val isFavourite = favouritesUiState.favourites.find { favourite ->
                    favourite.trackName == track.track.name && favourite.artistName == track.artistName
                } != null
                HorizontalTrackItem(
                    isFavourite = isFavourite,
                    trackUiState = track,
                    onFavouriteClick = onFavouriteClick,
                )
            }
        }
    }
}
