package ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mvi.QiitaIntent
import mvi.QiitaViewState
import repository.QiitaRepository

class QiitaArticleViewModel(
    private val repository: QiitaRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(QiitaViewState())
    val state: StateFlow<QiitaViewState> = _state

    private val _id = MutableStateFlow("")
    val id: StateFlow<String> = _id

    fun setId(id: String) {
        _id.value = id
    }

    fun handleIntent(
        intent: QiitaIntent,
    ) {
        viewModelScope.launch {
            when (intent) {
                is QiitaIntent.LoadQiitaArticleList -> fetchQiita()
                is QiitaIntent.RetryQiitaArticleList -> retryQiita()
                is QiitaIntent.GetQiitaArticl -> fetchArticle(intent.id)
            }
        }
    }

    private suspend fun retryQiita() {
        _state.value = _state.value.copy(
            loading = true,
            error = null,
        )

        try {
            val articleList = repository.getItems()
            _state.value = QiitaViewState(
                loading = false,
                articleList = articleList,
            )
        } catch (e: Exception) {
            _state.value = _state.value.copy(
                loading = false,
                error = e.message,
            )
        }
    }

    private suspend fun fetchQiita() {
        _state.value = _state.value.copy(
            loading = true,
            error = null,
        )
        try {
            val articleList = repository.getItems()
            _state.value = QiitaViewState(
                loading = false,
                articleList = articleList,
            )
        } catch (e: Exception) {
            _state.value = _state.value.copy(
                loading = false,
                error = e.message,
            )
        }
    }

    private suspend fun fetchArticle(id: String) {
        _state.value = _state.value.copy(
            loading = true,
            error = null,
        )
        try {
            val article = repository.getArticle(id)
            _state.value = QiitaViewState(
                loading = false,
                article = article,
            )
        } catch (e: Exception) {
            _state.value = _state.value.copy(
                loading = false,
                error = e.message,
            )
        }
    }
}