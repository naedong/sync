package kr.tr.finance.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kr.tr.finance.data.model.request.GptRequest
import kr.tr.finance.domain.model.ChatGptModel
import kr.tr.finance.domain.model.InsuranceGood

/**
 * Finance
 * Created by Naedong
 * Date: 2023-10-12(012)
 * Time: 오후 2:03
 */
fun interface  getUserInsuranceData : (GptRequest) -> Flow<PagingData<InsuranceGood>>

fun interface  getUpdate : (GptRequest) -> Flow<PagingData<InsuranceGood>>