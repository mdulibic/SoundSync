package fer.drumre.soundsync.ui.profile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fer.digobr.kidslingo.theme.AppPrimary
import fer.digobr.kidslingo.theme.AppSecondary
import fer.drumre.soundsync.R
import fer.drumre.soundsync.data.model.ApiUser
import fer.drumre.soundsync.ui.profile.ProfileViewModel

@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel,
    onCloseClick: () -> Unit
) {
    val uiState by profileViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppPrimary),
    ) {
        Image(
            modifier = Modifier
                .size(56.dp)
                .padding(16.dp)
                .clickable {
                    onCloseClick()
                },
            painter = painterResource(id = R.drawable.ic_close),
            contentDescription = null,
        )
        uiState?.let {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
            ) {
                UserInfo(user = it.user)
                FollowingList(followingList = it.followingList)
                NonFollowingList(nonFollowingList = it.nonFollowingList)
            }
            OutlinedButton(
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.outlinedButtonColors(backgroundColor = AppSecondary),
                onClick = { profileViewModel.onLogoutClick() },
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                Text(
                    text = stringResource(R.string.log_out),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                )
            }
        }
    }
}

@Composable
fun UserInfo(user: ApiUser) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(AppSecondary, shape = CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "${user.name.first()}${user.surname.first()}",
                color = Color.White,
                fontSize = 24.sp,
            )
        }

        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            ProfileInfoRow(
                label = stringResource(R.string.name),
                value = user.name,
            )

            ProfileInfoRow(
                label = stringResource(R.string.surname),
                value = user.surname,
            )

            ProfileInfoRow(
                label = stringResource(R.string.email),
                value = user.email,
            )
        }
    }
}

@Composable
fun ProfileInfoRow(label: String, value: String) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = label,
            color = AppSecondary,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = value,
            color = Color.White,
            fontSize = 16.sp,
        )
    }
}

@Composable
fun FollowingList(followingList: List<ApiUser>) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stringResource(R.string.following),
            color = Color.White,
            fontSize = 16.sp,
        )
        followingList.forEach { followingUser ->
            Text(
                text = "${followingUser.name} ${followingUser.surname}",
                color = Color.White,
                fontSize = 16.sp,
            )
        }
    }
}

@Composable
fun NonFollowingList(nonFollowingList: List<ApiUser>) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stringResource(R.string.non_following),
            color = Color.White,
            fontSize = 16.sp,
        )
        nonFollowingList.forEach { nonFollowingUser ->
            Text(
                text = "${nonFollowingUser.name} ${nonFollowingUser.surname}",
                color = Color.White,
                fontSize = 16.sp,
            )
        }
    }
}
