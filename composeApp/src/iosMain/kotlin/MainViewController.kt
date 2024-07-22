import androidx.compose.ui.window.ComposeUIViewController
import koin.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }