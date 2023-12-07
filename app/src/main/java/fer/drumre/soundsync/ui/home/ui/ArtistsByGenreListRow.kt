package fer.drumre.soundsync.ui.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import fer.digobr.kidslingo.theme.AppSecondary
import fer.drumre.soundsync.data.model.Artist
import fer.drumre.soundsync.data.model.Track

@Composable
fun ArtistsByGenreListRow(
    artists: List<Artist>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(8.dp),
    ) {
        Text(
            modifier = Modifier
                .padding(top = 12.dp),
            text = artists.first().genre,
            color = AppSecondary,
            style = MaterialTheme.typography.h5,
        )
        artists.drop(1).forEach {
            ArtistCard(artist = it)
        }
    }
}

@Composable
private fun ArtistCard(
    artist: Artist,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            CoverImage(imageUrl = artist.imageUrl)
            Text(
                modifier = Modifier
                    .padding(12.dp),
                text = artist.name,
                color = Color.White,
                style = MaterialTheme.typography.body1,
            )
        }
        Text(
            modifier = Modifier
                .padding(horizontal = 12.dp),
            text = "Top Tracks",
            color = AppSecondary,
            style = MaterialTheme.typography.h6,
        )
        artist.tracks.take(5).forEach {
            TrackItem(track = it)
        }
    }
}

@Composable
private fun CoverImage(
    imageUrl: String,
    imageHeight: Int = 200,
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
            .wrapContentHeight()
            .clip(RoundedCornerShape(4.dp)),
    )
}

@Composable
private fun TrackItem(
    track: Track,
) {
//    Row {
//        CoverImage(imageUrl = track.imageUrl, imageHeight = 200)
    Text(
        modifier = Modifier
            .padding(12.dp),
        text = track.name,
        color = Color.White,
        style = MaterialTheme.typography.body1,
    )
//    }
}
