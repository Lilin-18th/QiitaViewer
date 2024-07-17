import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.QiitaArticle
import ui.theme.QiitaAppTheme

@Composable
@Preview
fun App() {
    QiitaAppTheme {
        QiitaArticle()
    }
}
