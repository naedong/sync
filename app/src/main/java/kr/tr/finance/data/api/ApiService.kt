package kr.tr.finance.data.api

import kr.tr.finance.data.model.ResponseChatGptModel
import kr.tr.finance.data.model.ResponseKyoboInsuranceModel
import kr.tr.finance.data.model.ResponseResult
import kr.tr.finance.data.model.ResponseUserModel
import kr.tr.finance.data.model.request.GptRequest
import kr.tr.finance.data.model.request.Message
import kr.tr.finance.domain.model.ChatGptModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Finance
 * Created by Naedong
 * Date: 2023-10-05
 * Time: 오후 4:17
 */
interface ApiService {

    @FormUrlEncoded
    @POST("user/sign")
    suspend fun getUserDataModel(
        @Field("id") id : String,
        @Field("password") password : String
    ) : Response<ResponseResult>


    @GET("chat/messages")
    suspend fun getChatGpt( @Query("model") model : String, @Query("messages") messages : List<Message>  ) : Response<ResponseChatGptModel>


    @POST("kyobo/inquiremyinsurance")
    suspend fun getUserInsurance(
    ) : Response<ResponseKyoboInsuranceModel>

}