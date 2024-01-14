package fer.drumre.soundsync.ui.favourites.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fer.digobr.kidslingo.theme.AppPrimary
import fer.digobr.kidslingo.theme.AppSecondary
import fer.drumre.soundsync.ui.favourites.FavouritesViewModel
import fer.drumre.soundsync.ui.home.model.Favourite
import fer.drumre.soundsync.ui.view.HeaderRow

@Composable
fun FavouritesScreen(
    favouritesViewModel: FavouritesViewModel,
    onFavouriteClick: (Favourite) -> Unit,
    onInitialClick: () -> Unit,
) {
    val uiState by favouritesViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppPrimary)
            .padding(bottom = 56.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HeaderRow(
            initial = favouritesViewModel.initial,
            onInitialClick = onInitialClick,
        )
        Text(
            text = "Favourites",
            color = AppSecondary,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h5,
        )
        Divider(
            modifier = Modifier.height(4.dp).width(100.dp).background(Color.White).padding(4.dp),
        )
        uiState?.let {
            if(it.favourites.isEmpty()) {
                // TODO: Set empty state
            } else {
                FavouritesList(
                    favouritesUiState = it,
                    onFavouriteClick = onFavouriteClick,
                )
            }
        }
    }
}
