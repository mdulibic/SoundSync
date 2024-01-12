package fer.drumre.soundsync.ui.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import fer.digobr.kidslingo.theme.AppGrey
import fer.digobr.kidslingo.theme.AppSecondary
import fer.drumre.soundsync.data.model.Artist
import fer.drumre.soundsync.data.model.Track

@Composable
fun ArtistsByGenreListRow(
    artists: List<Artist>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = artists.first().genre,
            color = AppSecondary,
            style = MaterialTheme.typography.h5,
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(artists.drop(1)) {
                ArtistCard(artist = it)
            }
        }
    }
}

@Composable
private fun ArtistCard(
    artist: Artist,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .background(AppGrey, RoundedCornerShape(8.dp))
            .width(300.dp)
            .padding(16.dp),
    ) {
        Row() {
            CoverImage(imageUrl = artist.imageUrl)
            Text(
                modifier = Modifier
                    .padding(12.dp),
                fontSize = 24.sp,
                text = artist.name,
                color = Color.White,
                style = MaterialTheme.typography.body1,
            )
        }
        Text(
            text = "Top Tracks",
            color = AppSecondary,
            style = MaterialTheme.typography.h6,
        )
        artist.tracks.take(3).forEach {
            TrackItem(track = it)
        }
    }
}

@Composable
private fun CoverImage(
    imageUrl: String,
    imageHeight: Int = 100,
    modifier: Modifier = Modifier,
) {
    val painter = rememberImagePainter(
        data = imageUrl,
        builder = {
            crossfade(true)
        },
    )
    Image(
        painter = painter,
        contentDescription = "",
        modifier = modifier
            .height(imageHeight.dp)
            .clip(RoundedCornerShape(4.dp)),
    )
}

@Composable
private fun TrackItem(
    track: Track,
) {
    Row(
        modifier = Modifier.height(60.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CoverImage(imageUrl = track.imageUrl, imageHeight = 50)
        Text(
            modifier = Modifier
                .padding(12.dp),
            text = track.name,
            fontSize = 16.sp,
            color = Color.White,
            maxLines = 2,
            softWrap = true,
            style = MaterialTheme.typography.body1,
        )
    }
}
