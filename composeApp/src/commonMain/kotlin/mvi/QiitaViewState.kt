package mvi

import model.QiitaArticle
import model.QiitaArticleList

data class QiitaViewState(
    val loading: Boolean = false,
    val articleList: List<QiitaArticleList> = emptyList(),
    val article: QiitaArticle? = null,
    val error: String? = null,
)
