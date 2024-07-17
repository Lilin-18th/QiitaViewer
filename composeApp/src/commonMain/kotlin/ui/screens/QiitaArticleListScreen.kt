package ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import model.QiitaArticleList
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import repository.QiitaRepository
import ui.component.RotateAnimation

@Composable
fun QiitaArticleListScreen(
    list: List<QiitaArticleList>,
) {
    if (list.isEmpty()) {
        RotateAnimation()
    }

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(list) { item ->
            QiitaListView(
                name = item.user.name,
                createDate = item.createDate,
                title = item.title,
                likes = item.likes,
            )
        }
    }
}

@Composable
fun QiitaListView(
    name: String,
    createDate: String,
    title: String,
    likes: Int?,
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
                if (name.isNotEmpty()) {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White,
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                }

                Text(
                    text = createDate,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                )
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 20.sp,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(5.dp))

                Row {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "likes",
                    )
                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        text = likes.toString(),
                        fontSize = 16.sp,
                        color = Color.White,
                    )
                }
            }
        }
    }
}