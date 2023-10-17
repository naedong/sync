package kr.tr.finance.data.model

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Finance
 * Created by Naedong
 * Date: 2023-10-12(012)
 * Time: 오전 10:17
 */

@Keep
@JsonClass(generateAdapter = true)
data class ResponseChatGptModel(
    @Json(name = "result")
    val result : String
)