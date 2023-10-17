package kr.tr.finance.domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kr.tr.finance.data.model.request.GptRequest
import kr.tr.finance.domain.model.ChatGptModel
import kr.tr.finance.domain.model.InsuranceGood
import kr.tr.finance.domain.repository.getUserInsuranceData
import javax.inject.Inject

/**
 * Finance
 * Created by Naedong
 * Date: 2023-10-12(012)
 * Time: 오후 2:44
 */
class InsuranceUseCase @Inject constructor(
    val repository : getUserInsuranceData
) {
    fun getInsuranceData(request : GptRequest, update : Boolean) : Flow<PagingData<InsuranceGood>> {
        if(update){
            return repository.invoke(request)
        }else {
            return repository.invoke(request)
        }
    }

}