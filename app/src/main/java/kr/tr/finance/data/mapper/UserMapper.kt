package kr.tr.finance.data.mapper

import kr.tr.finance.data.model.ResponseResult
import kr.tr.finance.data.model.ResponseUserModel
import kr.tr.finance.domain.model.UserModel
import kr.tr.finance.domain.model.UserResult
import retrofit2.Response


/**
 * Finance
 * Created by Naedong
 * Date: 2023-10-06
 * Time: 오전 10:21
 */
fun Response<ResponseResult>.asUser() : Response<UserResult> {
    return Response.success(body()?.let {
        UserResult(
            it.result.asUser()
         )
    })
}

fun ResponseUserModel.asUser() : UserModel {
    return UserModel(
        id ?: "",
        password ?: ""
    )
}