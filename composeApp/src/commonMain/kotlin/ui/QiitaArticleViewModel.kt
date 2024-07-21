package ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import model.QiitaArticleList
import mvi.QiitaIntent
import mvi.QiitaViewState
import repository.QiitaRepository

class QiitaArticleViewModel: ViewModel() {

    private val qiitaRepository = QiitaRepository()

    private val _state = MutableStateFlow(QiitaViewState())
    val state: StateFlow<QiitaViewState> = _state

    fun onClickArticle(id: String) {
        Logger.d { "onClickArticle: $id" }
    }

    fun handleIntent(intent: QiitaIntent) {
        viewModelScope.launch {
            when (intent) {
                is QiitaIntent.LoadQiitaArticle -> fetchQiita()
                is QiitaIntent.RetryQiitaArticle -> retryQiita()
            }
        }
    }

    private suspend fun retryQiita() {
        _state.value = _state.value.copy(
            loading = true,
            error = null,
        )

        try {
            val articleList = qiitaRepository.getItems()
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
            val articleList = qiitaRepository.getItems()
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
}