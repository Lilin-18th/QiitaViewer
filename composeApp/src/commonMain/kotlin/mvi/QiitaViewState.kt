package mvi

import model.QiitaArticleList

data class QiitaViewState(
    val loading: Boolean = false,
    val articleList: List<QiitaArticleList> = emptyList(),
    val error: String? = null,
)
