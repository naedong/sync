package kr.tr.finance.di

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kr.tr.finance.BuildConfig
import kr.tr.finance.data.api.ApiService
import okhttp3.Call
import okhttp3.Dispatcher
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import java.util.logging.Level
import java.util.logging.Logger
import javax.inject.Singleton

/**
 * Finance
 * Created by Naedong
 * Date: 2023-10-05
 * Time: 오후 4:20
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     *  @author Naedong
     *
     *  @annotation @Singleton
     *  @explanation Injector를 통해 단 한번 인스턴스화 시키기 위함
     *
     *  @annotation @Provides
     *  @explanation 종속 항목을 정의하기 위한 annotation
     *
     *  @fun provideOkhttpClient
     *  @explanation OkhttpClient 를 설정
     *
     *  @param loggingInterceptor: HttpLoggingInterceptor
     *  @explanation 로깅 인터셉터를 추가하여 HTTP 요청과 응답을 로깅
     *
     *
     *  @return OkHttpClient
     *  @explanation 생성된 OkHttpClient
     */


    @Singleton
    @Provides
    fun provideOkhttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .callTimeout(30, TimeUnit.SECONDS) // 호출 타이머 30초
            .connectTimeout(30, TimeUnit.SECONDS) // 연결 타이머 30초
            .readTimeout(30, TimeUnit.SECONDS) // 읽기 타이머 30초
            .writeTimeout(30, TimeUnit.SECONDS) // 쓰기 타이머 30초
            .addInterceptor(loggingInterceptor) // 사용자 지정 인터셉터 추가
            .addInterceptor { chain ->
                val original = chain.request()
                    .newBuilder()
                    .build()

                val originalHttpUrl: HttpUrl = original.url
                val url = originalHttpUrl.newBuilder()
//                    .addQueryParameter(
//                        "serviceKey",
//                        ""
//                    )
                    .build()

                val requestBuilder: Request.Builder = original.newBuilder()
                    .url(url)

                val newRequest: Request = requestBuilder.build()

                val client = OkHttpClient.Builder()
                    .dispatcher(Dispatcher().apply { maxRequestsPerHost = 1 })
                    .build()

                client.newCall(newRequest).enqueue(object : okhttp3.Callback {

                    override fun onFailure(call: Call, e: IOException) {

                        Log.e("Finance", "onFailure $e")
                    }

                    override fun onResponse(call: Call, response: Response) {
                        Log.e("Finance", "onResponseCheck $response")

                    }
                })

                chain.proceed(newRequest)

            }
            .build()
    }



    /**
     *  @author Naedong
     *
     *  @annotation @Singleton
     *  @explanation Injector를 통해 단 한번 인스턴스화 시키기 위함
     *
     *  @annotation @Provides
     *  @explanation 종속 항목을 정의하기 위한 annotation
     *
     *  @fun provideLoggingInterceptor
     *  @explanation  HttpLoggingInterceptor를 설정 반환하기 위한 Fun
     *
     *  @return HttpLoggingInterceptor
     *  @explanation HttpLoggingInterceptor 반환
     */

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }


    /**
     *  @author Naedong
     *
     *  @annotation @Singleton
     *  @explanation Injector를 통해 단 한번 인스턴스화 시키기 위함
     *
     *  @annotation @Provides
     *  @explanation 종속 항목을 정의하기 위한 annotation
     *
     *  @fun provideApiService
     *  @explanation retrofitClient로 설정된 값을 가져와 retrofitClient 생성 ApiService
     *
     *  @param retrofitClient: Retrofit
     *  @explanation retrofitClient 인스턴스
     *
     *  @return ApiService
     *  @explanation retrofitClint를 통해 ApiService 구현체 생성
     */
    @Singleton
    @Provides
    fun provideApiService(retrofitClient: Retrofit): ApiService {
        return retrofitClient.create(ApiService::class.java)
    }



    /**
     *  @author Naedong
     *
     *  @annotation @Singleton
     *  @explanation Injector를 통해 단 한번 인스턴스화 시키기 위함
     *
     *  @annotation @Provides
     *  @explanation 종속 항목을 정의하기 위한 annotation
     *
     *  @fun provideRetrofit
     *  @explanation Retrofit 설정하기 위한 함수
     *
     *  @param okHttpClient: OkHttpClient
     *  @explanation OkHttpClient 인스턴스를 호출
     *
     *  @return Retrofit
     *
     */


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        Logger.getLogger(OkHttpClient::class.java.name).level = Level.FINE
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.URL_BASE)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

}