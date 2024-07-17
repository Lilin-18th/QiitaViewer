package ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import model.QiitaArticleList
import model.Tags
import org.jetbrains.compose.resources.InternalResourceApi
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
                tags = item.tags,
                likes = item.likes,
                imageUrl = item.user.userImage,
            )
        }
    }
}

@Composable
fun QiitaListView(
    name: String,
    createDate: String,
    title: String,
    tags: List<Tags>,
    likes: Int?,
    imageUrl: String,
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
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.Gray, CircleShape),
                    model = imageUrl,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(5.dp))

                Column {
                    if (name.isNotEmpty()) {
                        Text(
                            text = name,
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold,
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                    }

                    Text(
                        text = createDate,
                        style = MaterialTheme.typography.bodySmall,
                    )
                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Spacer(modifier = Modifier.height(5.dp))

                    if (tags.isNotEmpty()) {
                        LazyRow {
                            items(tags) { tag ->
                                Card(
                                    modifier = Modifier
                                        .padding(end = 5.dp),
                                    shape = RoundedCornerShape(5.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .background(Color.Gray),
                                    ) {
                                        Text(
                                            text = tag.name,
                                            modifier = Modifier.padding(all = 3.dp),
                                            style = MaterialTheme.typography.bodySmall,
                                            textAlign = TextAlign.Center,
                                            color = Color.White,
                                        )
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                    }

                    Row {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "likes",
                        )
                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = likes.toString(),
                            fontSize = 16.sp,
                        )
                    }
                }
            }
        }
    }
}