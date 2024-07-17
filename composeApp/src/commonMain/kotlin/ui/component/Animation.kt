package ui.component

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import qiitaviewer.composeapp.generated.resources.Res
import qiitaviewer.composeapp.generated.resources.qiita_icon

@Composable
fun RotateAnimation() {
    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween<Float>(
                durationMillis = 3000,
                easing = FastOutLinearInEasing,
            ),
        )
    )

    Box(
        modifier = Modifier
            .size(100.dp),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            modifier = Modifier.rotate(rotation),
            painter = painterResource(Res.drawable.qiita_icon),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
    }
}