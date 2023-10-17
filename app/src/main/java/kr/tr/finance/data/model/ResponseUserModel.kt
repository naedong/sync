package kr.tr.finance.data.model

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Finance
 * Created by Naedong
 * Date: 2023-10-05
 * Time: 오후 4:18
 */

@Keep
@JsonClass(generateAdapter = true)
data class ResponseResult(

    @Json(name = "result")
    val result : ResponseUserModel

)

@Keep
@JsonClass(generateAdapter = true)
data class ResponseUserModel(
    @Json(name = "id")
    val id : String? = null,
    @Json(name = "password")
    val password : String? = null
)