package kr.tr.finance.data.pagingsource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kr.tr.finance.data.remote.RemoteDatasource
import kr.tr.finance.domain.model.UserModel
import javax.inject.Inject

/**
 * Finance
 * Created by Naedong
 * Date: 2023-10-11(011)
 * Time: 오전 10:40
 */
class UserPagingDataSource @Inject constructor(
    private val remoteDataSource: RemoteDatasource,
    private val id : String,
    private val pwd  : String
) : PagingSource<Int, UserModel>() {



    /**
     *  @author Naedong
     *
     *  @fun getRefreshKey
     *  @explanation 정확 초기 키를 매핑하기 위한 함수
     *
     *  @param state: PagingState<Int, ExchangeRateItem>
     *  @explanation PagingState
     *
     *  @return Int?
     *  @explanation source 위치 변경
     *
     */

    override fun getRefreshKey(state: PagingState<Int, UserModel>): Int? = state.anchorPosition


    /**
     *  @author Naedong
     *
     *  @fun load
     *  @explanation 초기 키를 매핑하기 위한 함수
     *
     *  @param params: LoadParams<Int>
     *  @explanation 실행 작업에 관한 정보
     *
     *  @return LoadResult<Int, ExchangeRateItem>
     *  @explanation 작업 결과
     *
     */


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserModel> {
        try {
            val nextPage = params.key ?: 1

            val response = remoteDataSource.getUserDataModel(id, pwd)

            val list = response.body()?.result

            val lists = listOf<UserModel>(list ?: UserModel("", ""))

            return LoadResult.Page(
                data = lists,
                nextKey = nextPage.plus(1),
                prevKey = if (nextPage == 1) null
                else nextPage.minus(1)
            )
        } catch (e: Exception) {
                throw e
        }
    }
}