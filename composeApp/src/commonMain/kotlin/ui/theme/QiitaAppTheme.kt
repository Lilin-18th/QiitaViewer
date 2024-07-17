package ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primaryContainer = LimeGreen,
    background = WhiteSmoke,
    surfaceVariant = Color.White,
    onSurfaceVariant = Color.Black,
)

private val DarkColors = darkColorScheme(
    primaryContainer = DarkGreen,
    background = Color.Black,
    surfaceVariant = Dimgray,
    onSurfaceVariant = Color.Black,
)

@Composable
fun QiitaAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        darkTheme -> DarkColors
        else -> LightColors
    }
    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}