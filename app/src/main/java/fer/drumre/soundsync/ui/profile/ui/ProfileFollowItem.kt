package fer.drumre.soundsync.ui.profile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fer.digobr.kidslingo.theme.AppGrey
import fer.digobr.kidslingo.theme.AppSecondary
import fer.drumre.soundsync.R
import fer.drumre.soundsync.data.model.ApiUser

@Composable
fun FollowerItem(
    isFollowing: Boolean,
    apiUser: ApiUser,
    onFollowClick: (String) -> Unit,
) {
    val ctaRes = if (isFollowing) R.string.following else R.string.follow
    val backgroundColor = if (isFollowing) AppSecondary else AppGrey
    val textColor = if (isFollowing) Color.White else Color.White
    Row(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(AppSecondary),
            ) {
                Text(
                    text = "${apiUser.name.first()}${apiUser.surname.first()}",
                    color = Color.White,
                    fontSize = 12.sp,
                    modifier = Modifier.align(Alignment.Center),
                )
            }
            Text(
                text = "${apiUser.name} ${apiUser.surname}",
                color = Color.White,
                fontSize = 16.sp,
            )
        }

        OutlinedButton(
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.outlinedButtonColors(backgroundColor = backgroundColor),
            onClick = { onFollowClick(apiUser.id) },
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.wrapContentWidth(),
        ) {
            Text(
                text = stringResource(ctaRes),
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                color = textColor,
            )
        }
    }
}
