package fer.drumre.soundsync.ui.explore.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import fer.digobr.kidslingo.theme.AppSecondary
import fer.drumre.soundsync.R
import fer.drumre.soundsync.ui.explore.model.Recommendation
import fer.drumre.soundsync.ui.favourites.model.FavouritesUiState
import fer.drumre.soundsync.ui.home.model.Favourite
import fer.drumre.soundsync.ui.home.model.TrackUiState
import fer.drumre.soundsync.ui.home.ui.TrackItem

@Composable
fun RecommendationFavorite(
    subtitleRes: Int = R.string.because_you_liked,
    recommendation: Recommendation,
    favouritesUiState: FavouritesUiState,
    onFavouriteClick: (Favourite) -> Unit,
) {
    Column(
        modifier = Modifier.padding(8.dp),
    ) {
        Text(
            text = stringResource(subtitleRes).uppercase(),
            color = Color.White,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.caption,
        )
        Text(
            text = recommendation.artistName,
            color = AppSecondary,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.subtitle1,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            recommendation.tracks.forEach { track ->
                val isFavourite = favouritesUiState.favourites.find { favourite ->
                    favourite.trackName == track.name && favourite.artistName == track.artist
                } != null
                TrackItem(
                    isFavourite = isFavourite,
                    trackUiState = TrackUiState(
                        artistName = recommendation.artistName,
                        track = track,
                    ),
                    onFavouriteClick = onFavouriteClick,
                )
            }
        }
    }
}
