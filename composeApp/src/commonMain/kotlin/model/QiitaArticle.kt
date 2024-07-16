package model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QiitaArticleList(
    val id: String,
    val title: String,
    val url: String,
    val user: User,
)

@Serializable
data class QiitaArticle(
    val id: String,
    val title: String,
    val url: String,
    @SerialName("updated_at")
    val date: String,
    val user: User,
    val body: String,
)

@Serializable
data class User(
    val name: String?,
    val description: String?,
)
