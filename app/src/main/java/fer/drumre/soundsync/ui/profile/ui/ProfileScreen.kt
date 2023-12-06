package fer.drumre.soundsync.ui.profile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fer.digobr.kidslingo.theme.AppPrimary
import fer.drumre.soundsync.ui.profile.ProfileViewModel

@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel,
) {
    // val uiState by homeViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppPrimary)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
    }
}
