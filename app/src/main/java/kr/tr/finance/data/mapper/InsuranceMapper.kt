package kr.tr.finance.data.mapper

import kr.tr.finance.data.model.ResponseBody
import kr.tr.finance.data.model.ResponseBodyX
import kr.tr.finance.data.model.ResponseGood
import kr.tr.finance.data.model.ResponseGoodCnt
import kr.tr.finance.data.model.ResponseHeader
import kr.tr.finance.data.model.ResponseHeaderX
import kr.tr.finance.data.model.ResponseInsuranceResult
import kr.tr.finance.data.model.ResponseKYOBORESPONSE
import kr.tr.finance.data.model.ResponseKyoboInsuranceModel
import kr.tr.finance.data.model.ResponseRequest
import kr.tr.finance.data.model.ResponseResponse
import kr.tr.finance.domain.model.InsuranceBody
import kr.tr.finance.domain.model.InsuranceBodyX
import kr.tr.finance.domain.model.InsuranceGood
import kr.tr.finance.domain.model.InsuranceGoodCnt
import kr.tr.finance.domain.model.InsuranceHeader
import kr.tr.finance.domain.model.InsuranceHeaderX
import kr.tr.finance.domain.model.InsuranceKyoboReseponse
import kr.tr.finance.domain.model.InsuranceRequest
import kr.tr.finance.domain.model.InsuranceResponse
import kr.tr.finance.domain.model.InsuranceResult
import kr.tr.finance.domain.model.KyoboInsuranceModel
import retrofit2.Response

/**
 * Finance
 * Created by Naedong
 * Date: 2023-10-12(012)
 * Time: 오후 1:11
 */

fun Response<ResponseKyoboInsuranceModel>.asInsurance(): Response<KyoboInsuranceModel> {
    return Response.success(body()?.let {
            KyoboInsuranceModel(
                it.result.asInsurance()
            )
        }
    )
}


fun ResponseInsuranceResult.asInsurance(): InsuranceResult {
    return InsuranceResult(
        request.asInsurance(),
        response.asInsurance()
    )
}

fun ResponseResponse.asInsurance(): InsuranceResponse {
    return InsuranceResponse(
        body.asInsurance(),
        header.asInsurance(),
        statusCode
    )
}


fun ResponseRequest.asInsurance(): InsuranceRequest {
    return InsuranceRequest(
        body.asInsurance(),
        header.asInsurance()
    )
}


fun ResponseHeader.asInsurance(): InsuranceHeader {
    return InsuranceHeader(
        authorization,
        contentType
    )
}

fun ResponseBody.asInsurance(): InsuranceBody {
    return InsuranceBody(
        afonCd,
        reqRspnScCd,
        svcId,
        traDt,
        traSrno
    )
}

fun ResponseHeaderX.asInsurance(): InsuranceHeaderX {
    return InsuranceHeaderX(
        accessControlAllowCredentials,
        accessControlAllowMethods,
        accessControlAllowOrigin,
        contentType,
        date,
        server,
        setCookie,
        transferEncoding
    )
}


fun ResponseBodyX.asInsurance(): InsuranceBodyX {
    return InsuranceBodyX(
        goodList = goodList.map { it.asInsurance() },
        goodListCnt = goodListCnt.map { it.asInsurance() },
        kyoboResponse = kyoboResponse.map { it.asInsurance() }
    )
}


fun ResponseKYOBORESPONSE.asInsurance(): InsuranceKyoboReseponse {
    return InsuranceKyoboReseponse(
        reqRspnScCd,
        rspnCd,
        rspnMsgNm,
        svcId,
        traSrno
    )
}


fun ResponseGoodCnt.asInsurance(): InsuranceGoodCnt {
    return InsuranceGoodCnt(
        goodListCnt
    )
}

fun ResponseGood.asInsurance(): InsuranceGood {
    return InsuranceGood(
        assrNm,
        conYmd,
        cpnyCd,
        cpnyEnsPvsNm,
        cpnyNm,
        endAmt,
        etncYmd,
        kcisEnsPvsCd,
        kcisEnsPvsNm,
        kcisEnsPvsStatCd,
        kcisEnsPvsStatNm,
        kcisGoodNm,
        kcisGoodScCd,
        kcisInqrTrgtRtnsCd,
        kcisMnprStatCd,
        kcisMnprStatNm,
        kcisPcyno,
        plhdNm,
        pmtCyclCd,
        pmtCyclNm,
        pmtpd,
        prm
    )

}