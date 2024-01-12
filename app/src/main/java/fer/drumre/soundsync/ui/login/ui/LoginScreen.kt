package fer.drumre.soundsync.ui.login.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import fer.digobr.kidslingo.theme.AppPrimary
import fer.drumre.soundsync.R
import fer.drumre.soundsync.ui.login.LoginViewModel
import fer.drumre.soundsync.ui.login.model.LoginSourceType

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel,
    onCtaClick: (LoginSourceType) -> Unit,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.login_animation))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppPrimary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            Text(
                text = stringResource(R.string.explore_your_favorite_music),
                color = Color.White,
                fontSize = 36.sp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 32.dp),
            )

            LottieAnimation(
                modifier = Modifier,
                composition = composition,
                iterations = LottieConstants.IterateForever,
            )
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(32.dp)),
            onClick = {
                onCtaClick(LoginSourceType.FB)
            },
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = stringResource(R.string.login_with_fb),
                color = Color.Black,
                fontSize = 16.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
            )
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(32.dp)),
            onClick = {
                onCtaClick(LoginSourceType.GOOGLE)
            },
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = stringResource(R.string.login_with_google),
                color = Color.Black,
                fontSize = 16.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
            )
        }
    }
}
