package fer.drumre.soundsync.ui.home.ui.viewholders

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import fer.digobr.kidslingo.theme.AppGrey
import fer.digobr.kidslingo.theme.AppSecondary
import fer.drumre.soundsync.R
import fer.drumre.soundsync.ui.favourites.model.FavouritesUiState
import fer.drumre.soundsync.ui.home.model.Favourite
import fer.drumre.soundsync.ui.home.model.TagsToExploreUiState
import fer.drumre.soundsync.ui.home.ui.ArtistCircleCard
import fer.drumre.soundsync.ui.home.ui.ArtistTrackList
import fer.drumre.soundsync.ui.home.ui.GenreCard

@Composable
fun TagsToExplore(
    favouritesUiState: FavouritesUiState,
    tagsToExploreUiState: TagsToExploreUiState,
    onGenreClick: (String) -> Unit,
    onArtistClick: (String) -> Unit,
    onFavouriteClick: (Favourite) -> Unit,
    modifier: Modifier = Modifier,
) {
    val painter = rememberImagePainter(
        data = tagsToExploreUiState.startGenre.featuredArtist.imageUrl,
        builder = {
            crossfade(true)
        },
    )
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(R.string.tags_to_explore),
                color = AppSecondary,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h5,
            )
            Divider(
                modifier = Modifier.height(4.dp).width(100.dp).background(Color.White),
            )
        }
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(tagsToExploreUiState.genres) {
                GenreCard(
                    imageUrl = it.featuredArtist.imageUrl,
                    title = it.name,
                    onClick = {
                        onGenreClick(it)
                    },
                )
            }
        }
        Column(
            modifier = Modifier
                .background(color = AppGrey, shape = RoundedCornerShape(8.dp))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Row {
                Text(
                    text = stringResource(R.string.tag),
                    color = AppSecondary,
                    style = MaterialTheme.typography.subtitle1,
                )
                Text(
                    text = tagsToExploreUiState.startGenre.name,
                    color = Color.White,
                    style = MaterialTheme.typography.subtitle1,
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            ) {
                Text(
                    text = stringResource(R.string.featured_artist),
                    color = AppSecondary,
                    style = MaterialTheme.typography.subtitle1,
                )
                Text(
                    text = tagsToExploreUiState.startGenre.featuredArtist.name,
                    color = Color.White,
                    style = MaterialTheme.typography.subtitle1,
                )
            }
            Image(
                painter = painter,
                contentDescription = "",
                modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape),
            )
            ArtistTrackList(
                tracks = tagsToExploreUiState.startGenre.featuredArtist.tracks,
                favouritesUiState = favouritesUiState,
                onFavouriteClick = onFavouriteClick,
            )
            Text(
                text = tagsToExploreUiState.startGenre.description,
                color = Color.White,
                style = MaterialTheme.typography.subtitle2,
            )
            LazyRow(
                modifier = modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(tagsToExploreUiState.startGenre.artists) {
                    ArtistCircleCard(
                        imageUrl = it.imageUrl,
                        title = it.name,
                        onClick = {
                            onArtistClick(it)
                        },
                    )
                }
            }
        }
    }
}
