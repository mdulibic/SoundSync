package fer.drumre.soundsync.ui.favourites.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fer.digobr.kidslingo.theme.AppSecondary
import fer.drumre.soundsync.R
import fer.drumre.soundsync.ui.favourites.model.FavouritesUiState
import fer.drumre.soundsync.ui.home.model.Favourite

@Composable
fun FavouritesList(
    favouritesUiState: FavouritesUiState,
    onFavouriteClick: (Favourite) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(favouritesUiState.favourites) {
            FavouriteItem(
                favourite = it,
                onFavouriteClick = onFavouriteClick,
            )
        }
    }
}

@Composable
private fun FavouriteItem(
    favourite: Favourite,
    onFavouriteClick: (Favourite) -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
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
            Column {
                Text(
                    modifier = Modifier
                        .padding(2.dp),
                    text = favourite.artistName,
                    fontSize = 16.sp,
                    color = Color.White,
                    maxLines = 2,
                    softWrap = true,
                    style = MaterialTheme.typography.body1,
                )
                Text(
                    modifier = Modifier
                        .padding(2.dp),
                    text = favourite.trackName,
                    fontSize = 16.sp,
                    color = AppSecondary,
                    maxLines = 2,
                    softWrap = true,
                    style = MaterialTheme.typography.body1,
                )
            }
        }
        Image(
            painter = painterResource(R.drawable.ic_star_selected),
            contentDescription = "",
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    onFavouriteClick(favourite)
                },
        )
    }
}
