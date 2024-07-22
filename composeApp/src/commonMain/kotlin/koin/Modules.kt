package koin

import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import repository.QiitaRepository
import repository.QiitaRepositoryImpl
import ui.QiitaArticleViewModel

val sharedModule = module {
    singleOf(::QiitaRepositoryImpl).bind<QiitaRepository>()
    viewModelOf(::QiitaArticleViewModel)
}