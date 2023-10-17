package kr.tr.finance.presentation.navi

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import kr.tr.finance.common.item.NavigationItem
import kr.tr.finance.presentation.model.MainViewModel
import kr.tr.finance.presentation.view.home.HomeComponent
import kr.tr.finance.presentation.view.login.LoginPage

/**
 * Finance
 * Created by Naedong
 * Date: 2023-10-06
 * Time: 오전 11:19
 */

fun NavGraphBuilder.homeScreen(
    navController: NavHostController,
    paddingValues: PaddingValues,
    viewModel: MainViewModel
) {

    composable(
        route = NavigationItem.home.route,
    ) {
        HomeComponent(navController, paddingValues, viewModel)
    }
    composable(
        route = NavigationItem.login.route,
    ) {
        LoginPage(paddingValues, navController, viewModel)
    }




}
