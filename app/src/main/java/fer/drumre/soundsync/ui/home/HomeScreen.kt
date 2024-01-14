package fer.drumre.soundsync.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fer.digobr.kidslingo.theme.AppPrimary
import fer.drumre.soundsync.ui.home.ui.viewholders.TagsToExplore
import fer.drumre.soundsync.ui.view.HeaderRow
import fer.drumre.soundsync.ui.view.LoadingIndicator

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    onInitialClick: () -> Unit,
) {
    val scrollState = rememberLazyListState()
    val uiState by homeViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 56.dp)
            .background(AppPrimary),
    ) {
        HeaderRow(
            initial = homeViewModel.initial,
            onInitialClick = onInitialClick,
        )
        LazyColumn(
            modifier = Modifier.padding(16.dp),
            state = scrollState,
        ) {
            uiState?.let { homeUiState ->
                item {
                    TagsToExplore(
                        favouritesUiState = homeUiState.favouritesUiState,
                        tagsToExploreUiState = homeUiState.tagsToExploreUiState,
                        onGenreClick = {
                            homeViewModel.onGenreClick(it)
                        },
                        onArtistClick = {
                            homeViewModel.onArtistClick(it)
                        },
                        onFavouriteClick = {
                            homeViewModel.onFavouriteClick(it)
                        },
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
