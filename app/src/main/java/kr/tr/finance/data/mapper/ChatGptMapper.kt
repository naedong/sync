package kr.tr.finance.data.mapper

import kr.tr.finance.common.util.insuranceRegex
import kr.tr.finance.data.model.ResponseChatGptModel
import kr.tr.finance.domain.model.ChatGptModel
import retrofit2.Response

/**
 * Finance
 * Created by Naedong
 * Date: 2023-10-12(012)
 * Time: 오전 10:23
 */
fun Response<ResponseChatGptModel>.asChat() : Response<ChatGptModel> {
    return Response.success(body()?.let {
        val regexResult  = insuranceRegex(" ${it.result}")
        ChatGptModel(
            regexResult
        )
    })
}
