package kr.tr.finance.domain.model

/**
 * Finance
 * Created by Naedong
 * Date: 2023-10-05
 * Time: 오후 4:19
 */
data class UserResult(
    val result : UserModel
)


data class UserModel(
    val id : String,
    val password : String
)
