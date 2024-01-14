package fer.drumre.soundsync.ui.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fer.digobr.kidslingo.theme.AppSecondary
import fer.drumre.soundsync.R
import fer.drumre.soundsync.ui.home.model.Favourite
import fer.drumre.soundsync.ui.home.model.TrackUiState

@Composable
fun TrackItem(
    isFavourite: Boolean,
    trackUiState: TrackUiState,
    onFavouriteClick: (Favourite) -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier.weight(1f),
        ) {
            Box(
                modifier = Modifier.size(48.dp).background(AppSecondary, RoundedCornerShape(2.dp)),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_music),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp).align(Alignment.Center),
                )
            }
            Text(
                modifier = Modifier
                    .padding(12.dp),
                text = trackUiState.track.name,
                fontSize = 16.sp,
                color = Color.White,
                maxLines = 2,
                softWrap = true,
                style = MaterialTheme.typography.body1,
            )
        }
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
