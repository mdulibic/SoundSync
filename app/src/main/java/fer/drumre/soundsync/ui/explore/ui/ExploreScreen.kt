package fer.drumre.soundsync.ui.explore.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import fer.digobr.kidslingo.theme.AppPrimary
import fer.digobr.kidslingo.theme.AppSecondary
import fer.drumre.soundsync.R
import fer.drumre.soundsync.ui.explore.ExploreViewModel
import fer.drumre.soundsync.ui.view.HeaderRow
import fer.drumre.soundsync.ui.view.LoadingIndicator

@Composable
fun ExploreScreen(
    exploreViewModel: ExploreViewModel,
    onInitialClick: () -> Unit,
) {
    val uiState by exploreViewModel.uiState.collectAsState()
    val scrollState = rememberLazyListState()

    Column(
        modifier = Modifier
            .padding(bottom = 56.dp)
            .background(AppPrimary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        HeaderRow(
            initial = exploreViewModel.initial,
            onInitialClick = onInitialClick,
        )
        Text(
            text = stringResource(R.string.explore),
            color = AppSecondary,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h5,
        )
        Divider(
            modifier = Modifier.height(4.dp).width(100.dp).background(Color.White),
        )
        LazyColumn(
            modifier = Modifier.padding(16.dp),
            state = scrollState,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            uiState?.let {
                item {
                    GeoTopTracks(
                        geoTopTracksUiState = it.geoTopTracksUiState,
                        favouritesUiState = it.favouritesUiState,
                        onFavouriteClick = {
                            exploreViewModel.onFavouriteClick(it)
                        },
                    )
                }
                item {
                    RecommendationsFavourites(
                        recommendationsFavoritesUiState = it.recommendationsFavorites,
                        favoritesUiState = it.favouritesUiState,
                        onFavouriteClick = {
                            exploreViewModel.onFavouriteClick(it)
                        },
                    )
                }
                item {
                    RecommendationsFollowees(
                        recommendationsFolloweesUiState = it.recommendationsFollowees,
                        favoritesUiState = it.favouritesUiState,
                        onFavouriteClick = {
                            exploreViewModel.onFavouriteClick(it)
                        }
                    )
                }
            } ?: run {
                item {
                    LoadingIndicator()
                }
            }
        }
    }
}
