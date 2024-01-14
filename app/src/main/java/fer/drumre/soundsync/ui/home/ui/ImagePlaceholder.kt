package fer.drumre.soundsync.ui.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@Composable
fun ImagePlaceholder(
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
            .height(imageHeight.dp),
    )
}
