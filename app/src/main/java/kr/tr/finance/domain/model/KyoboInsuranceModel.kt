package kr.tr.finance.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Finance
 * Created by Naedong
 * Date: 2023-10-12(012)
 * Time: 오후 1:12
 */


data class KyoboInsuranceModel(
    val result: InsuranceResult,

    )

data class InsuranceResult(

     val request: InsuranceRequest,
    val response: InsuranceResponse
)

data class InsuranceRequest(
    val body: InsuranceBody,
    val header: InsuranceHeader
)

data class InsuranceBody(
    val afonCd: String,
    val reqRspnScCd: String,
    val svcId: String,
    val traDt: String,
    val traSrno: String
)


data class InsuranceHeader(
    val authorization: String,
    val contentType : String
)

data class InsuranceHeaderX(
    val accessControlAllowCredentials: List<String>,
    val accessControlAllowMethods: List<String>,
    val accessControlAllowOrigin: List<String>,
    val contentType: List<String>,
    val date: List<String>,
    val server: List<String>,
    val setCookie: List<String>,
    val transferEncoding: List<String>
)

data class InsuranceResponse(
    val body: InsuranceBodyX,
    val header: InsuranceHeaderX,
    val statusCode: Int
)
@Serializable
data class InsuranceBodyX(
    @SerialName("kyoboResponse")
    val kyoboResponse: List<InsuranceKyoboReseponse>?,
    @SerialName("goodList")
    var goodList: List<InsuranceGood>?,
    @SerialName("goodListCnt")
    val goodListCnt: List<InsuranceGoodCnt>?
)

@Serializable
data class InsuranceKyoboReseponse(
    @SerialName("reqRspnScCd")
    val reqRspnScCd: String,
    @SerialName("rspnCd")
    val rspnCd: String,
    @SerialName("rspnMsgNm")
    val rspnMsgNm: String,
    @SerialName("svcId")
    val svcId: String,
    @SerialName("traSrno")
    val traSrno: String
)
@Serializable
data class InsuranceGoodCnt(
    @SerialName("goodListCnt")
    val goodListCnt: String
)


@Serializable
data class InsuranceGood(
    @SerialName("assrNm")
    val assrNm: String,

    @SerialName("conYmd")
    val conYmd: String,

    @SerialName("cpnyCd")
    val cpnyCd: String,

    @SerialName("cpnyEnsPvsNm")
    val cpnyEnsPvsNm: String,
    @SerialName("cpnyNm")
    val cpnyNm: String,
    @SerialName("endAmt")
    val endAmt: String,
    @SerialName("etncYmd")
    val etncYmd: String,
    @SerialName("kcisEnsPvsCd")
    val kcisEnsPvsCd: String,
    @SerialName("kcisEnsPvsNm")
    val kcisEnsPvsNm: String,
    @SerialName("kcisEnsPvsStatCd")
    val kcisEnsPvsStatCd: String,
    @SerialName("kcisEnsPvsStatNm")
    val kcisEnsPvsStatNm: String,
    @SerialName("kcisGoodNm")
    val kcisGoodNm: String,
    @SerialName("kcisGoodScCd")
    val kcisGoodScCd: String,
    @SerialName("kcisInqrTrgtRtnsCd")
    val kcisInqrTrgtRtnsCd: String,
    @SerialName("kcisMnprStatCd")
    val kcisMnprStatCd: String,
    @SerialName("kcisMnprStatNm")
    val kcisMnprStatNm: String,
    @SerialName("kcisPcyno")
    val kcisPcyno: String,
    @SerialName("plhdNm")
    val plhdNm: String,
    @SerialName("pmtCyclCd")
    val pmtCyclCd: String,
    @SerialName("pmtCyclNm")
    val pmtCyclNm: String,
    @SerialName("pmtpd")
    val pmtpd: String,
    @SerialName("prm")
    val prm: String
)