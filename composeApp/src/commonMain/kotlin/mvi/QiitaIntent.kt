package mvi

sealed class QiitaIntent {
    data object LoadQiitaArticle : QiitaIntent()
    data object RetryQiitaArticle: QiitaIntent()
}