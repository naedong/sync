package kr.tr.finance.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Finance
 * Created by Naedong
 * Date: 2023-10-12(012)
 * Time: 오전 11:21
 */
@Serializable
data class GptRequest(
    val model: String = "gpt-3.5-turbo",
    val messages: List<Message>?
)
@Serializable
data class Message(
    @SerialName("role")
    val role : String = "user",
    @SerialName("content")
    val content : String
)
