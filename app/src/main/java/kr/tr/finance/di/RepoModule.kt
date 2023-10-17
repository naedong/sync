package kr.tr.finance.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.tr.finance.data.repositoryimpl.InsuranceRepositoryImpl
import kr.tr.finance.data.repositoryimpl.UpdateRepositoryImpl
import kr.tr.finance.data.repositoryimpl.UserRepositoryImpl
import kr.tr.finance.domain.repository.UserRepository
import kr.tr.finance.domain.repository.getUpdate
import kr.tr.finance.domain.repository.getUserInsuranceData
import javax.inject.Singleton

/**
 * Finance
 * Created by Naedong
 * Date: 2023-10-05
 * Time: 오후 4:21
 */

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    /**
     *  @author Naedong
     *
     *  @annotation @Singleton
     *  @explanation Injector를 통해 단 한번 인스턴스화 시키기 위함
     *
     *  @annotation @Provides
     *  @explanation 종속 항목을 정의하기 위한 annotation
     *
     *  @fun provideRepository
     *  @explanation Repository 종속성을 주입
     *
     *  @param repositoryImpl : RepositoryImpl
     *  @explanation repositoryImpl 정보
     *
     *
     *  @return Repository
     *  @explanation repositoryImpl을 Repository에 주입
     */


    @Singleton
    @Provides
    fun provideRepository(
        repositoryImpl : UserRepositoryImpl
    ) : UserRepository = repositoryImpl

    @Singleton
    @Provides
    fun provideInsuranceRepository(
        repositoryImpl : InsuranceRepositoryImpl
    ) : getUserInsuranceData = repositoryImpl

    @Singleton
    @Provides
    fun provideUpdateRepository(
        repositoryImpl : UpdateRepositoryImpl
    ) : getUpdate = repositoryImpl



}