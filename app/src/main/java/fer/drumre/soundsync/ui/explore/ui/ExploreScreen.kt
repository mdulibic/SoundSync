package fer.drumre.soundsync.ui.explore.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import fer.digobr.kidslingo.theme.AppPrimary
import fer.drumre.soundsync.ui.explore.ExploreViewModel
import fer.drumre.soundsync.ui.view.HeaderRow

@Composable
fun ExploreScreen(
    exploreViewModel: ExploreViewModel,
    onInitialClick: () -> Unit
) {
    // val uiState by favouritesViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppPrimary),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HeaderRow(
            initial = exploreViewModel.initial,
            onInitialClick = onInitialClick
        )
    }
}
