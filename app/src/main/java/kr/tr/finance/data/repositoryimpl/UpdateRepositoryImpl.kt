package kr.tr.finance.data.repositoryimpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kr.tr.finance.data.local.LocalDataStore
import kr.tr.finance.data.model.request.GptRequest
import kr.tr.finance.data.pagingsource.UpdatePagingDataSource
import kr.tr.finance.data.remote.RemoteDatasource
import kr.tr.finance.domain.model.InsuranceGood
import kr.tr.finance.domain.repository.getUpdate
import javax.inject.Inject

/**
 * Finance
 * Created by Naedong
 * Date: 2023-10-17(017)
 * Time: 오후 1:06
 */
class UpdateRepositoryImpl @Inject constructor(
    private val dataSource: RemoteDatasource,
    private val localDataSource: LocalDataStore,
) : getUpdate {

    override fun invoke(p1: GptRequest): Flow<PagingData<InsuranceGood>> {
        return Pager(
            PagingConfig(
                pageSize = 10,
                prefetchDistance = 2,
                enablePlaceholders = true
            )
        ) {
            UpdatePagingDataSource(
                dataSource,
                localDataSource,
                p1,
            )
        }.flow
    }
}