package kr.tr.finance.data.pagingsource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kr.tr.finance.data.local.LocalDataStore
import kr.tr.finance.data.model.request.GptRequest
import kr.tr.finance.data.remote.RemoteDatasource
import kr.tr.finance.domain.model.InsuranceBodyX
import kr.tr.finance.domain.model.InsuranceGood
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

/**
 * Finance
 * Created by Naedong
 * Date: 2023-10-12(012)
 * Time: 오후 2:00
 */
class InsurancePagingDataSource @Inject constructor(
    private val remoteDataSource: RemoteDatasource,
    private val localDataSource : LocalDataStore,
    private val p1 : GptRequest,
) : PagingSource<Int, InsuranceGood>() {

    override fun getRefreshKey(state: PagingState<Int, InsuranceGood>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult.Page<Int, InsuranceGood> {



        try {
            val nextPage = params.key ?: 1
            val localDataStores = localDataSource.getChatData.first()
        if( localDataStores == null ){
            val filterItem = remoteDataSource.getChatGpt(p1).body()?.result
            delay(1000L)

            val response = remoteDataSource.getUserInsurance()

            val saveList = response.body()?.result?.response?.body

            val list = response.body()?.result?.response?.body?.goodList

            val filteringItem = list?.filter { it.cpnyNm.contains(filterItem ?: "보험") }

            val saveListFilter = saveList?.goodList?.filter { it.cpnyNm.contains(filterItem ?: "보험") }

            saveList.also {
                it?.goodList = saveListFilter
            }

            localDataSource.setRequestData(p1)
            localDataSource.setChatData(saveList ?: InsuranceBodyX(goodListCnt = null, goodList = null, kyoboResponse = null) )

            return LoadResult.Page(
                data = filteringItem ?: emptyList(),
                nextKey = nextPage.plus(1),
                prevKey = null
            )
        } else {
            var goodslist : InsuranceBodyX? = localDataStores
            val localDataKey = localDataSource.getRequestData.first()
            if(p1.messages?.first()?.content != localDataKey){
                localDataSource.clearData()

                val filterItem = remoteDataSource.getChatGpt(p1).body()?.result

                delay(1000L)

                val response = remoteDataSource.getUserInsurance()


                val saveList = response.body()?.result?.response?.body

                val list = response.body()?.result?.response?.body?.goodList

                val filteringItem = list?.filter { it.cpnyNm.contains(filterItem ?: "보험") }


                val saveListFilter = saveList?.goodList?.filter { it.cpnyNm.contains(filterItem ?: "보험") }

                saveList.also {
                    it?.goodList = saveListFilter
                }

                localDataSource.setRequestData(p1)
                localDataSource.setChatData(saveList ?: InsuranceBodyX(goodListCnt = null, goodList = null, kyoboResponse = null) )
                return LoadResult.Page(
                    data = filteringItem ?: emptyList(),
                    nextKey = nextPage.plus(1),
                    prevKey = null
                )

            }

            return LoadResult.Page(
                data = goodslist?.goodList ?: emptyList(),
                nextKey = nextPage.plus(1),
                prevKey = null
            )

        }

        } catch (e: Exception) {
            when (e) {
                is UnknownHostException -> {
                  Log.e("Insurance  UnknownHostException Excption", "$e")
                    throw  e
                }
                is SocketTimeoutException -> {
                    Log.e("Insurance Excption", "$e")
                    throw  e
                }
            }
            return throw  e
        }
    }
}