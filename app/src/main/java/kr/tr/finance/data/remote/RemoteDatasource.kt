package kr.tr.finance.data.remote

import android.util.Log
import kr.tr.finance.data.api.ApiService
import kr.tr.finance.data.mapper.asChat
import kr.tr.finance.data.mapper.asInsurance
import kr.tr.finance.data.mapper.asUser
import kr.tr.finance.data.model.request.GptRequest
import kr.tr.finance.domain.model.ChatGptModel
import kr.tr.finance.domain.model.KyoboInsuranceModel
import kr.tr.finance.domain.model.UserModel
import kr.tr.finance.domain.model.UserResult
import retrofit2.Response
import javax.inject.Inject

/**
 * Finance
 * Created by Naedong
 * Date: 2023-10-10(010)
 * Time: 오전 9:16
 */
class RemoteDatasource @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getUserDataModel(id : String, pwd : String ) : Response<UserResult> {
        return apiService.getUserDataModel(id, pwd).asUser()
    }

    suspend fun getUserInsurance() : Response<KyoboInsuranceModel> {
        return apiService.getUserInsurance().asInsurance()
    }

    suspend fun getChatGpt(request : GptRequest ) : Response<ChatGptModel> {
        return apiService.getChatGpt(request.model, request?.messages ?: emptyList()).asChat()
    }

}