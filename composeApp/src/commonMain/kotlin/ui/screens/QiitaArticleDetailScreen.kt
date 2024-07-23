package ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import mvi.QiitaIntent
import ui.QiitaArticleViewModel
import ui.component.RotateAnimation

@Composable
fun QiitaArticleDetailScreen(
    viewModel: QiitaArticleViewModel,
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.handleIntent(QiitaIntent.GetQiitaArticl(viewModel.id.value))
    }

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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
            ) {
                Text(
                    text = state.article!!.body,
                )
            }
        }
    }
}