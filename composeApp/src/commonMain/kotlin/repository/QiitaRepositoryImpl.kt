package repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import model.QiitaArticle
import model.QiitaArticleList

class QiitaRepositoryImpl : QiitaRepository {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }
    }

    override suspend fun getItems(): List<QiitaArticleList> {
        return client.get("$BASE_URL/api/v2/items").body()
    }

    override suspend fun getArticle(id: String?): QiitaArticle {
        return client.get("$BASE_URL/api/v2/items/$id").body()
    }

    companion object {
        private const val BASE_URL = "https://qiita.com"
    }
}