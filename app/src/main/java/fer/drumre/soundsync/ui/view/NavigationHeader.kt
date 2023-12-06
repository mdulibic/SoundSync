package fer.drumre.soundsync.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fer.digobr.kidslingo.theme.AppSecondary
import fer.drumre.soundsync.R

@Composable
fun HeaderRow(
    initial: String?,
    onSearchClick: (() -> Unit)? = null,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f),
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_music),
                contentDescription = null,
                modifier = Modifier.size(40.dp),
            )
        }

        Image(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = null,
            modifier = Modifier.size(24.dp).clickable {
                if (onSearchClick != null) {
                    onSearchClick()
                }
            },
        )
        Spacer(modifier = Modifier.width(16.dp))
        NameInitialIcon(initial = initial)
    }
}

@Composable
private fun NameInitialIcon(initial: String?, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(36.dp)
            .background(
                shape = CircleShape,
                color = AppSecondary,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            modifier = modifier.padding(4.dp),
            text = initial ?: "NN",
            fontSize = 16.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
        )
    }
}
