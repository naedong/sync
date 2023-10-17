package kr.tr.finance.presentation.view.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import kr.tr.finance.data.model.request.GptRequest
import kr.tr.finance.data.model.request.Message
import kr.tr.finance.domain.model.ChatGptModel
import kr.tr.finance.presentation.model.MainViewModel

/**
 * Finance
 * Created by Naedong
 * Date: 2023-10-06
 * Time: 오전 11:28
 */
@Composable
fun HomeComponent(
    navController: NavHostController,
    paddingValues: PaddingValues,
    viewModel: MainViewModel,
) {



    ListScreen(paddingValues, navController, viewModel)



}