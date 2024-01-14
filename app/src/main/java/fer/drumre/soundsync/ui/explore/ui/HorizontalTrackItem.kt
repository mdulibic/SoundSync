package fer.drumre.soundsync.ui.explore.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import fer.digobr.kidslingo.theme.AppGrey
import fer.digobr.kidslingo.theme.AppSecondary
import fer.drumre.soundsync.R
import fer.drumre.soundsync.ui.home.model.Favourite
import fer.drumre.soundsync.ui.home.model.TrackUiState

@Composable
fun HorizontalTrackItem(
    isFavourite: Boolean,
    trackUiState: TrackUiState,
    onFavouriteClick: (Favourite) -> Unit,
) {
    Column(
        modifier = Modifier.background(
            color = AppGrey,
            shape = RoundedCornerShape(8.dp),
        ).padding(8.dp).width(200.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(AppSecondary, RoundedCornerShape(2.dp)),
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_music),
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.Center),
            )
        }
        Text(
            text = trackUiState.artistName,
            color = Color.White,
            textAlign = TextAlign.Center,
            maxLines = 1,
            style = MaterialTheme.typography.body1,
        )
        Text(
            text = trackUiState.track.name,
            color = AppSecondary,
            textAlign = TextAlign.Center,
            maxLines = 1,
            style = MaterialTheme.typography.body1,
        )
        Image(
            painter = painterResource(
                id = if (isFavourite) {
                    R.drawable.ic_star_selected
                } else {
                    R.drawable.ic_star
                },
            ),
            contentDescription = "",
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    onFavouriteClick(
                        Favourite(
                            artistName = trackUiState.artistName,
                            trackName = trackUiState.track.name,
                        ),
                    )
                },
        )
    }
}
