package kr.tr.finance.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.delay
import kr.tr.finance.data.local.LocalDataStore
import kr.tr.finance.data.model.request.GptRequest
import kr.tr.finance.data.remote.RemoteDatasource
import kr.tr.finance.domain.model.InsuranceBodyX
import kr.tr.finance.domain.model.InsuranceGood
import javax.inject.Inject

/**
 * Finance
 * Created by Naedong
 * Date: 2023-10-17(017)
 * Time: 오후 1:07
 */
class UpdatePagingDataSource @Inject constructor(
    private val remoteDataSource: RemoteDatasource,
    private val localDataSource : LocalDataStore,
    private val p1 : GptRequest,
) : PagingSource<Int, InsuranceGood>() {

    override fun getRefreshKey(state: PagingState<Int, InsuranceGood>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, InsuranceGood> {
        localDataSource.clearData()

        val nextPage = params.key ?: 1

        val filterItem = remoteDataSource.getChatGpt(p1).body()?.result

        delay(1000L)

        val response = remoteDataSource.getUserInsurance()

        val saveList = response.body()?.result?.response?.body

        val list = response.body()?.result?.response?.body?.goodList

        val filteringItem = list?.filter { it.cpnyNm.contains(filterItem ?: "보험") }

        val saveListFilter = saveList?.goodList?.filter { it.cpnyNm.contains(filterItem ?: "보험") } as InsuranceBodyX?

        localDataSource.setChatData(saveListFilter ?: InsuranceBodyX(goodListCnt = null, goodList = null, kyoboResponse = null))

        return LoadResult.Page(
            data = filteringItem ?: emptyList(),
            nextKey = nextPage.plus(1),
            prevKey = null
        )
    }
}