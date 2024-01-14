package fer.drumre.soundsync.ui.home.ui.viewholders

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import fer.digobr.kidslingo.theme.AppGrey
import fer.digobr.kidslingo.theme.AppSecondary
import fer.drumre.soundsync.ui.favourites.model.FavouritesUiState
import fer.drumre.soundsync.ui.home.model.Favourite
import fer.drumre.soundsync.ui.home.model.Top50TracksUiState
import fer.drumre.soundsync.ui.home.ui.TrackItem

@Composable
fun Top50Tracks(
    top50TracksUiState: Top50TracksUiState,
    favouritesUiState: FavouritesUiState,
    onFavouriteClick: (Favourite) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Top 50 playlist",
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
        Column(
            modifier = Modifier
                .background(color = AppGrey, shape = RoundedCornerShape(8.dp))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            top50TracksUiState.tracksList.forEach {
                Text(
                    text = it.artistName,
                    color = AppSecondary,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.body1,
                )
                TrackItem(
                    isFavourite = favouritesUiState.favourites.find { favourite ->
                        favourite.trackName == it.track.name && favourite.artistName == it.artistName
                    } != null,
                    trackUiState = it,
                    onFavouriteClick = onFavouriteClick,
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
