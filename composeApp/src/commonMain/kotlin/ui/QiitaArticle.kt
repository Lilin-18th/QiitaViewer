package ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import mvi.QiitaIntent
import org.jetbrains.compose.resources.StringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import qiitaviewer.composeapp.generated.resources.Res
import qiitaviewer.composeapp.generated.resources.article_detail
import qiitaviewer.composeapp.generated.resources.article_list
import ui.component.CenteredAppBar
import ui.screens.QiitaArticleDetailScreen
import ui.screens.QiitaArticleListScreen

enum class QiitaScreens(val title: StringResource) {
    ArticleList(title = Res.string.article_list),
    ArticleDetail(title = Res.string.article_detail),
}

@OptIn(KoinExperimentalAPI::class)
@Composable
fun QiitaArticle(
    navController: NavHostController = rememberNavController(),
) {
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = QiitaScreens.valueOf(
        backStackEntry?.destination?.route ?: QiitaScreens.ArticleList.name
    )

    val viewModel = koinViewModel<QiitaArticleViewModel>()

    Scaffold(
        topBar = {
            CenteredAppBar(
                title = currentScreen.title.key,
                isNavigationBack = navController.previousBackStackEntry != null,
                onClickBack = { navController.popBackStack() }
            )
        },
        floatingActionButton = {
            if (currentScreen == QiitaScreens.ArticleList) {
                FloatingActionButton(
                    onClick = {
                        viewModel.handleIntent(QiitaIntent.RetryQiitaArticleList)
                    },
                    content = {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = null,
                        )
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = QiitaScreens.ArticleList.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(route = QiitaScreens.ArticleList.name) {
                QiitaArticleListScreen(
                    viewModel = viewModel,
                    onClickArticle = { navController.navigate(QiitaScreens.ArticleDetail.name) }
                )
            }
            composable(route = QiitaScreens.ArticleDetail.name) {
                QiitaArticleDetailScreen(
                    viewModel = viewModel,
                )
            }
        }
    }
}