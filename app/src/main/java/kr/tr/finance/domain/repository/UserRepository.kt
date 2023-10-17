package kr.tr.finance.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kr.tr.finance.domain.model.UserModel

/**
 * Finance
 * Created by Naedong
 * Date: 2023-10-10(010)
 * Time: 오전 10:07
 */
interface UserRepository {
    suspend fun setUserData(id : String, pwss : String) : UserModel?
}