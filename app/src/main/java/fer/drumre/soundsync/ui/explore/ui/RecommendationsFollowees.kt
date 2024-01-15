package fer.drumre.soundsync.ui.explore.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
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
import fer.drumre.soundsync.data.model.ApiTrack
import fer.drumre.soundsync.ui.explore.model.Recommendation
import fer.drumre.soundsync.ui.explore.model.RecommendationsFolloweesUiState
import fer.drumre.soundsync.ui.favourites.model.FavouritesUiState
import fer.drumre.soundsync.ui.home.model.Favourite

@Composable
fun RecommendationsFollowees(
    recommendationsFolloweesUiState: RecommendationsFolloweesUiState,
    favoritesUiState: FavouritesUiState,
    onFavouriteClick: (Favourite) -> Unit,
) {
    val list = recommendationsFolloweesUiState.recommendationsFollowees.entries.map {
        Recommendation(
            artistName = it.key,
            tracks = it.value,
        )
    }
    if (list.isNotEmpty()) {
        Column {
            Text(
                text = stringResource(R.string.based_on_people_you_follow),
                color = AppSecondary,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h6,
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
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                list.forEach {
                    RecommendationFavorite(
                        subtitleRes = R.string.because_your_connection,
                        recommendation = it,
                        favouritesUiState = favoritesUiState,
                        onFavouriteClick = onFavouriteClick,
                    )
                }
            }
        }
    }
}
