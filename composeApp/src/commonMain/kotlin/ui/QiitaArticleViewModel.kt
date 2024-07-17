package ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import model.QiitaArticleList
import repository.QiitaRepository

class QiitaArticleViewModel(
    qiitaRepository: QiitaRepository,
) : ViewModel() {
    private val _articleList = mutableStateOf<List<QiitaArticleList>>(emptyList())
    val articleList = _articleList

    init {
        viewModelScope.launch {
            _articleList.value = try {
                qiitaRepository.getItems()
            } catch (e: Exception) {
                emptyList()
            }
        }
    }
}