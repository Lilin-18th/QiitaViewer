package ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.resources.StringResource
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

@Composable
fun QiitaArticle(
    viewModel: QiitaArticleViewModel = androidx.lifecycle.viewmodel.compose.viewModel { QiitaArticleViewModel() },
    navController: NavHostController = rememberNavController(),
) {
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = QiitaScreens.valueOf(
        backStackEntry?.destination?.route ?: QiitaScreens.ArticleList.name
    )

    Scaffold(
        topBar = {
            CenteredAppBar(
                title = currentScreen.title.key,
                isNavigationBack = navController.previousBackStackEntry != null,
                onClickBack = { navController.popBackStack() }
            )
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = QiitaScreens.ArticleList.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(route = QiitaScreens.ArticleList.name) {
                QiitaArticleListScreen()
            }
            composable(route = QiitaScreens.ArticleDetail.name) {
                QiitaArticleDetailScreen()
            }
        }
    }
}