package fer.drumre.soundsync.ui.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fer.digobr.kidslingo.theme.AppGrey
import fer.digobr.kidslingo.theme.AppPrimary
import fer.drumre.soundsync.data.model.Genre
import fer.drumre.soundsync.ui.home.HomeViewModel
import fer.drumre.soundsync.ui.home.model.GenresUiState
import fer.drumre.soundsync.ui.view.HeaderRow
import fer.drumre.soundsync.ui.view.LoadingIndicator

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    onInitialClick: () -> Unit,
) {
    val scrollState = rememberScrollState()
    val uiState by homeViewModel.uiState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppPrimary)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HeaderRow(
            initial = homeViewModel.initial,
            onInitialClick = onInitialClick,
        )
        uiState?.let {
            GenresRow(genresUiState = it.genresUiState)
            ArtistsByGenreListRow(artists = it.artistsUiState.rockArtists)
        } ?: run {
            LoadingIndicator()
        }
    }
}

@Composable
private fun GenresRow(genresUiState: GenresUiState) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(genresUiState.genres) { genre ->
            GenreTile(genre = genre)
        }
    }
}

@Composable
fun GenreTile(genre: Genre) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(AppGrey, MaterialTheme.shapes.medium)
            .clickable { /* Handle genre click */ },
    ) {
        Text(
            modifier = Modifier
                .padding(12.dp),
            text = genre.name,
            style = MaterialTheme.typography.body2,
        )
    }
}
