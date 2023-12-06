package fer.drumre.soundsync.ui.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import fer.digobr.kidslingo.theme.AppPrimary
import fer.drumre.soundsync.ui.home.HomeViewModel
import fer.drumre.soundsync.ui.view.HeaderRow

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
) {
    // val uiState by homeViewModel.uiState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppPrimary),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HeaderRow(initial = homeViewModel.initial)
    }
}
