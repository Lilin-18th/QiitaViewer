package ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import model.QiitaArticleList
import repository.QiitaRepository

@Composable
fun QiitaArticleListScreen() {
    val scope = rememberCoroutineScope()
    val list = remember { mutableStateOf<List<QiitaArticleList>>(emptyList()) }
    LaunchedEffect(true) {
        scope.launch {
            list.value = try {
                QiitaRepository().getItems()
            } catch (e: Exception) {
                e.printStackTrace()
                emptyList()
            }
        }
    }
    if (list.value.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
    }

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(list.value) { item ->
            QiitaListView(
                name = item.user.name,
                title = item.title,
                description = item.user.description,
            )
        }
    }
}

@Composable
fun QiitaListView(
    name: String?,
    title: String,
    description: String?,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 10.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 10.dp),
        ) {
            Column {
                Text(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    textAlign = TextAlign.Start,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    text = title,
                )
                Spacer(modifier = Modifier.height(10.dp))

                Row {
                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        maxLines = 1,
                        color = Color.Gray.copy(alpha = 0.6f),
                        text = name ?: "No name",
                    )
                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        modifier = Modifier.padding(end = 10.dp),
                        maxLines = 1,
                        color = Color.Gray.copy(alpha = 0.6f),
                        text = description ?: "No description",
                    )
                }
            }
        }
    }
}