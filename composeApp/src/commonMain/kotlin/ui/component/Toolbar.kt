package ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import qiitaviewer.composeapp.generated.resources.Res
import qiitaviewer.composeapp.generated.resources.logo_background_color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenteredAppBar(
    title: String,
    isNavigationBack: Boolean,
//    isCloseScreen: Boolean,
    onClickBack: () -> Unit,
) {
    CenterAlignedTopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 5.dp),
        title = {
            Image(
                modifier = Modifier.size(100.dp),
                painter = painterResource(Res.drawable.logo_background_color),
                contentDescription = null,
            )
        },
        navigationIcon = {
            if (isNavigationBack) {
                IconButton(
                    onClick = { onClickBack() }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Dog",
                    )
                }
            }
        },
        actions = {
//            if (isCloseScreen) {
//                IconButton(
//                    onClick = { onClickClose() }
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.Close,
//                        contentDescription = "",
//                    )
//                }
//            }
        },
    )
}