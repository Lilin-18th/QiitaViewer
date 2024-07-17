package model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QiitaArticleList(
    val id: String,
    val title: String,
    val url: String,
    @SerialName("likes_count")
    val likes: Int?,
    @SerialName("created_at")
    val createDate: String,
    val tags: List<Tags>,
    val user: User,
)

@Serializable
data class Tags(
    val name: String,
)

@Serializable
data class User(
    val name: String,
    val description: String?,
    @SerialName("profile_image_url")
    val userImage: String,
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

