package fer.digobr.kidslingo.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import fer.drumre.soundsync.theme.appTypography

private val darkColorScheme = darkColors(
    primary = AppSecondary,
    secondary = AppPrimary,
)

private val lightColorScheme = lightColors(
    primary = AppSecondary,
    secondary = AppPrimary,

    background = AppPrimary,
    surface = AppPrimary,
    onPrimary = Color.White,
)

@Composable
fun SoundSyncTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> darkColorScheme
        else -> lightColorScheme
    }

    MaterialTheme(
        colors = colorScheme,
        typography = appTypography,
        content = content
    )
}
