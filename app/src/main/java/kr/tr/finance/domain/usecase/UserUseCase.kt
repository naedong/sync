package kr.tr.finance.domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kr.tr.finance.data.model.request.GptRequest
import kr.tr.finance.domain.model.InsuranceGood
import kr.tr.finance.domain.model.UserModel
import kr.tr.finance.domain.repository.UserRepository
import javax.inject.Inject

/**
 * Finance
 * Created by Naedong
 * Date: 2023-10-11(011)
 * Time: 오전 10:54
 */
class UserUseCase @Inject constructor(
    private val repository: UserRepository
){

   suspend fun setLogin(id : String, pwd : String ) : UserModel? {
        return repository.setUserData(id, pwd)
    }




}