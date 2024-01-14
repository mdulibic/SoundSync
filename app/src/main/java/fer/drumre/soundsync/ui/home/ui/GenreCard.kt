package fer.drumre.soundsync.ui.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@Composable
fun GenreCard(
    imageUrl: String,
    title: String,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
) {
    val painter = rememberImagePainter(
        data = imageUrl,
        builder = {
            crossfade(true)
        },
    )
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Gray)
            .clickable { onClick(title) },
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .alpha(0.5f)
                .size(150.dp),
        )

        Text(
            modifier = Modifier.align(Alignment.Center).padding(8.dp),
            text = title,
            style = MaterialTheme.typography.subtitle1,
            color = Color.White,
        )
    }
}
