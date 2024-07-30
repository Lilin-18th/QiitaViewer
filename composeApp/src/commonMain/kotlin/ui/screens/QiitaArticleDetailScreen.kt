package ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewState
import ui.QiitaArticleViewModel
import ui.component.RotateAnimation

@Composable
fun QiitaArticleDetailScreen(
    viewModel: QiitaArticleViewModel,
) {
    val state by viewModel.state.collectAsState()

    when {
        state.loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                RotateAnimation()
            }
        }

        state.error != null -> {
            Text(
                text = "Error: ${state.error}",
            )
        }

        state.article != null -> {
            val webViewState = rememberWebViewState(state.article!!.url)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
            ) {
                WebView(webViewState)
            }
        }
    }
}