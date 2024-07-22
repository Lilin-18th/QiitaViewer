package repository

import model.QiitaArticle
import model.QiitaArticleList

interface QiitaRepository {
    suspend fun getItems(): List<QiitaArticleList>
    suspend fun getArticle(id: String?): QiitaArticle
}