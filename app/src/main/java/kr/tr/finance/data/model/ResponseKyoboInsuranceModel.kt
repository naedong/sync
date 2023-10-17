package kr.tr.finance.data.model

import com.google.errorprone.annotations.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class ResponseKyoboInsuranceModel(
    @Json(name = "result")
    val result: ResponseInsuranceResult,

)

@JsonClass(generateAdapter = true)
data class ResponseInsuranceResult(

    @Json(name = "request")
    val request: ResponseRequest,
    @Json(name = "response")
    val response: ResponseResponse
)
@JsonClass(generateAdapter = true)
data class ResponseRequest(
    @Json(name = "body")
    val body: ResponseBody,
    @Json(name = "header")
    val header: ResponseHeader
)
@JsonClass(generateAdapter = true)
data class ResponseBody(
    @Json(name = "afonCd")
    val afonCd: String,
    @Json(name = "reqRspnScCd")
    val reqRspnScCd: String,
    @Json(name = "svcId")
    val svcId: String,
    @Json(name = "traDt")
    val traDt: String,
    @Json(name = "traSrno")
    val traSrno: String
)

@JsonClass(generateAdapter = true)
data class ResponseHeader(
    @Json(name = "Authorization")
    val authorization: String,

    @Json(name = "Content-Type")
    val contentType : String
)
@JsonClass(generateAdapter = true)
data class ResponseResponse(

    @Json(name = "body")
    val body: ResponseBodyX,
    @Json(name = "header")
    val header: ResponseHeaderX,

    @Json(name = "status_code")
    val statusCode: Int
)
@JsonClass(generateAdapter = true)
data class ResponseHeaderX(
    @Json(name = "Access-Control-Allow-Credentials")
    val accessControlAllowCredentials: List<String>,
    @Json(name = "Access-Control-Allow-Methods")
    val accessControlAllowMethods: List<String>,
    @Json(name = "Access-Control-Allow-Origin")
    val accessControlAllowOrigin: List<String>,
    @Json(name = "Content-Type")
    val contentType: List<String>,
    @Json(name = "Date")
    val date: List<String>,
    @Json(name = "Server")
    val server: List<String>,
    @Json(name = "Set-Cookie")
    val setCookie: List<String>,
    @Json(name = "Transfer-Encoding")
    val transferEncoding: List<String>
)

@JsonClass(generateAdapter = true)
data class ResponseBodyX(

    @Json(name = "KYOBO_RESPONSE")
    val kyoboResponse: List<ResponseKYOBORESPONSE>,
    @Json(name = "goodList")
    val goodList: List<ResponseGood>,
    @Json(name = "goodList_cnt")
    val goodListCnt: List<ResponseGoodCnt>
)

@JsonClass(generateAdapter = true)
data class ResponseKYOBORESPONSE(

    @Json(name = "reqRspnScCd")
    val reqRspnScCd: String,

    @Json(name = "rspnCd")
    val rspnCd: String,

    @Json(name = "rspnMsgNm")
    val rspnMsgNm: String,

    @Json(name = "svcId")
    val svcId: String,

    @Json(name = "traSrno")
    val traSrno: String
)
@JsonClass(generateAdapter = true)
data class ResponseGoodCnt(
    @Json(name = "goodList_cnt")
    val goodListCnt: String
)


@JsonClass(generateAdapter = true)
data class ResponseGood(
    @Json(name = "assrNm")
    val assrNm: String,
    @Json(name = "conYmd")
    val conYmd: String,
    @Json(name = "cpnyCd")
    val cpnyCd: String,
    @Json(name = "cpnyEnsPvsNm")
    val cpnyEnsPvsNm: String,
    @Json(name = "cpnyNm")
    val cpnyNm: String,
    @Json(name = "endAmt")
    val endAmt: String,
    @Json(name = "etncYmd")
    val etncYmd: String,
    @Json(name = "kcisEnsPvsCd")
    val kcisEnsPvsCd: String,
    @Json(name = "kcisEnsPvsNm")
    val kcisEnsPvsNm: String,
    @Json(name = "kcisEnsPvsStatCd")
    val kcisEnsPvsStatCd: String,
    @Json(name = "kcisEnsPvsStatNm")
    val kcisEnsPvsStatNm: String,
    @Json(name = "kcisGoodNm")
    val kcisGoodNm: String,
    @Json(name = "kcisGoodScCd")
    val kcisGoodScCd: String,
    @Json(name = "kcisInqrTrgtRtnsCd")
    val kcisInqrTrgtRtnsCd: String,
    @Json(name = "kcisMnprStatCd")
    val kcisMnprStatCd: String,
    @Json(name = "kcisMnprStatNm")
    val kcisMnprStatNm: String,
    @Json(name = "kcisPcyno")
    val kcisPcyno: String,
    @Json(name = "plhdNm")
    val plhdNm: String,
    @Json(name = "pmtCyclCd")
    val pmtCyclCd: String,
    @Json(name = "pmtCyclNm")
    val pmtCyclNm: String,
    @Json(name = "pmtpd")
    val pmtpd: String,
    @Json(name = "prm")
    val prm: String
)