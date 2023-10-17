package kr.tr.finance.presentation.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kr.tr.finance.data.local.LocalDataStore
import kr.tr.finance.data.model.request.GptRequest
import kr.tr.finance.domain.model.ChatGptModel
import kr.tr.finance.domain.model.UserModel
import kr.tr.finance.domain.usecase.InsuranceUseCase
import kr.tr.finance.domain.usecase.UserUseCase
import javax.inject.Inject

/**
 * Finance
 * Created by Naedong
 * Date: 2023-10-06
 * Time: 오전 12:05
 */

@HiltViewModel
class MainViewModel @Inject constructor(
    val userUseCase : UserUseCase,
    val insuranceUseCase : InsuranceUseCase,
) : ViewModel() {

    private var _UserId = MutableLiveData<String>()
    val userId : LiveData<String> = _UserId

    suspend fun getUser(id : String, pwd : String ) : UserModel? {
        val result = userUseCase.setLogin(id, pwd)
        _UserId.value =  result?.id
        return result
    }


    fun getInsurance(chat : GptRequest ) = insuranceUseCase.getInsuranceData(chat, false)
        .filterNotNull()
        .distinctUntilChanged()
        .cachedIn(viewModelScope)

    fun Update(chat: GptRequest) = insuranceUseCase.getInsuranceData(chat, true).cachedIn(viewModelScope)


}