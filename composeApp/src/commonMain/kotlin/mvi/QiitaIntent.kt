package mvi

sealed class QiitaIntent {
    data object LoadQiitaArticleList : QiitaIntent()
    data object RetryQiitaArticleList: QiitaIntent()
    data class GetQiitaArticl(val id: String): QiitaIntent()
}

