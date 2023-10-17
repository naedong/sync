package kr.tr.finance.data.repositoryimpl


import kr.tr.finance.data.remote.RemoteDatasource
import kr.tr.finance.domain.model.UserModel
import kr.tr.finance.domain.repository.UserRepository
import javax.inject.Inject

/**
 * Finance
 * Created by Naedong
 * Date: 2023-10-10(010)
 * Time: 오전 10:05
 */
class UserRepositoryImpl @Inject constructor(
    private val dataSource : RemoteDatasource
) : UserRepository {
    override suspend fun setUserData(id: String, pwss: String): UserModel? = dataSource.getUserDataModel(id, pwss).body()?.result

}