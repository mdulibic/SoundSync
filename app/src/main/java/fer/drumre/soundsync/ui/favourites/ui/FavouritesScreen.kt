package fer.drumre.soundsync.ui.favourites.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import fer.digobr.kidslingo.theme.AppPrimary
import fer.drumre.soundsync.ui.favourites.FavouritesViewModel
import fer.drumre.soundsync.ui.view.HeaderRow

@Composable
fun FavouritesScreen(
    favouritesViewModel: FavouritesViewModel,
    onInitialClick: () -> Unit,
) {
    // val uiState by favouritesViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppPrimary),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HeaderRow(
            initial = favouritesViewModel.initial,
            onInitialClick = onInitialClick,
        )
    }
}
